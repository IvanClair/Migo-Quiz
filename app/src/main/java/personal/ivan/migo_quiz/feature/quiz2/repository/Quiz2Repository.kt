package personal.ivan.migo_quiz.feature.quiz2.repository

import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.io.db.Quiz2Dao
import personal.ivan.migo_quiz.io.model.Quiz2IoModel
import personal.ivan.migo_quiz.io.util.Quiz2IoModelHelper
import javax.inject.Inject

class Quiz2Repository @Inject constructor(
    private val dao: Quiz2Dao,
    private val util: Quiz2IoModelHelper
) {

    /**
     * Get Quiz 2 list
     */
    suspend fun getQuiz2List(): List<Quiz2ListVhBindingModel> {
        val listFromDb = dao.loadAll()
        return listFromDb.map { Quiz2ListVhBindingModel(data = it, util = util) }
    }

    /**
     * Insert a [Quiz2IoModel] into database
     */
    suspend fun insetQuiz2(model: Quiz2IoModel): Unit = dao.insert(data = model)

    /**
     * Update a [Quiz2IoModel] into database
     */
    suspend fun updateQuiz2(model: Quiz2IoModel): Unit = dao.update(data = model)
}