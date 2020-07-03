package personal.ivan.migo_quiz.feature.quiz2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import personal.ivan.migo_quiz.feature.quiz2.interactor.Quiz2Interactor
import personal.ivan.migo_quiz.feature.quiz2.repository.Quiz2Repository
import personal.ivan.migo_quiz.feature.quiz2.routing.Quiz2Routing
import personal.ivan.migo_quiz.io.db.Quiz2Dao
import personal.ivan.migo_quiz.io.util.Quiz2IoModelHelper

@InstallIn(FragmentComponent::class)
@Module
object Quiz2DiModule {

    @FragmentScoped
    @Provides
    fun provideQuiz2Repository(
        dao: Quiz2Dao,
        util: Quiz2IoModelHelper
    ): Quiz2Repository = Quiz2Repository(dao = dao, util = util)

    @FragmentScoped
    @Provides
    fun provideQuiz2Interactor(util: Quiz2IoModelHelper): Quiz2Interactor =
        Quiz2Interactor(util = util)

    @FragmentScoped
    @Provides
    fun provideQuiz2IoModelHelper(): Quiz2IoModelHelper = Quiz2IoModelHelper()

    @FragmentScoped
    @Provides
    fun provideQuiz2Routing(): Quiz2Routing = Quiz2Routing()
}