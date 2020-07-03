package personal.ivan.migo_quiz.feature.quiz2.interactor

import androidx.annotation.IdRes
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.feature.quiz2.model.Quiz2ListVhBindingModel
import personal.ivan.migo_quiz.io.model.Quiz2Info
import personal.ivan.migo_quiz.io.model.Quiz2IoModel
import personal.ivan.migo_quiz.io.util.Quiz2IoModelHelper
import javax.inject.Inject

class Quiz2Interactor @Inject constructor(private val util: Quiz2IoModelHelper) {

    /**
     * Create [Quiz2IoModel] and insert to database
     */
    fun createQuiz2IoModel(
        value: String,
        @IdRes toggledButtonId: Int
    ): Quiz2IoModel = Quiz2IoModel(
        pass = util.createPass(),
        info = Quiz2Info(
            type =
            if (toggledButtonId == R.id.button_day) Quiz2Info.QUIZ_2_TYPE_DAY
            else Quiz2Info.QUIZ_2_TYPE_HOUR,
            value = value.toInt(),
            activateDateTime = "",
            dueDateTime = ""
        )
    )

    /**
     * Update a [Quiz2IoModel] to database
     */
    fun updateQuiz2IoModel(model: Quiz2ListVhBindingModel): Quiz2IoModel {
        val activatedDateTime = util.createActivateDateTime()
        val dueDateTime = util.getDueDateTime(
            type = model.type,
            activeDateTime = activatedDateTime,
            value = model.value
        )
        return Quiz2IoModel(
            pass = model.pass,
            info = Quiz2Info(
                type = model.type,
                value = model.value,
                activateDateTime = activatedDateTime,
                dueDateTime = dueDateTime
            )
        )
    }
}