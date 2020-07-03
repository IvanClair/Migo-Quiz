package personal.ivan.migo_quiz.io.db

import androidx.room.*
import personal.ivan.migo_quiz.io.model.Quiz2IoModel

// region Database

@Database(entities = [Quiz2IoModel::class], version = 1, exportSchema = false)
@TypeConverters(DbTypeConverter::class)
abstract class AppDb : RoomDatabase() {

    abstract fun quiz2Dao(): Quiz2Dao
}

// endregion

// region DAO

@Dao
interface Quiz2Dao {

    @Query("SELECT * FROM Quiz2IoModel")
    suspend fun loadAll(): List<Quiz2IoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Quiz2IoModel)

    @Update
    suspend fun update(data: Quiz2IoModel)

    @Delete
    suspend fun delete(data: Quiz2IoModel)
}

// endregion