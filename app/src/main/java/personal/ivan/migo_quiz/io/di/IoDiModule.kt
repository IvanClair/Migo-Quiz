package personal.ivan.migo_quiz.io.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import personal.ivan.migo_quiz.feature.quiz1.repository.Quiz1Repository
import personal.ivan.migo_quiz.io.db.AppDb
import personal.ivan.migo_quiz.io.db.Quiz2Dao
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object IoDiModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context): AppDb =
        Room
            .databaseBuilder(
                context,
                AppDb::class.java,
                context.packageName
            )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideQuiz2Dao(db: AppDb): Quiz2Dao = db.quiz2Dao()

    @Singleton
    @Provides
    fun provideQuiz1Repository(@ApplicationContext context: Context): Quiz1Repository =
        Quiz1Repository(context = context)
}