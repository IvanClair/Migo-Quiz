package personal.ivan.migo_quiz.io.util

import okhttp3.internal.toHexString
import personal.ivan.migo_quiz.io.model.Quiz2Info
import personal.ivan.migo_quiz.io.model.Quiz2IoModel
import personal.ivan.migo_quiz.util.getCurrentZonedDateString
import personal.ivan.migo_quiz.util.isAfterNow
import personal.ivan.migo_quiz.util.plusDay
import personal.ivan.migo_quiz.util.plusHour
import javax.inject.Inject

class Quiz2IoModelHelper @Inject constructor() {

    /**
     * Check [model] is expired or not
     */
    fun isQuiz2Expired(model: Quiz2IoModel): Boolean =
        model.info.dueDateTime.isAfterNow(datePattern = Quiz2Info.DATE_PATTERN)

    /**
     * Check [model] is activated or not
     */
    fun isActivated(model: Quiz2IoModel): Boolean =
        !isQuiz2Expired(model) && model.info.activateDateTime.isNotEmpty()

    /**
     * Check [model] is pending or not
     */
    fun isPending(model: Quiz2IoModel): Boolean =
        !isQuiz2Expired(model = model) && !isActivated(model = model)

    /**
     * Create pass base on current milliseconds
     */
    fun createPass(): String = System.currentTimeMillis().toHexString()

    /**
     * Create activate date time
     */
    fun createActivateDateTime(): String =
        getCurrentZonedDateString(datePattern = Quiz2Info.DATE_PATTERN)

    /**
     * Create due date time
     */
    fun getDueDateTime(
        @Quiz2Info.Companion.Quiz2Type type: Int,
        activeDateTime: String,
        value: Int
    ): String =
        when (type) {
            // hour
            Quiz2Info.QUIZ_2_TYPE_HOUR -> activeDateTime.plusHour(
                hour = value,
                datePattern = Quiz2Info.DATE_PATTERN
            )

            // day
            else -> activeDateTime.plusDay(
                day = value,
                datePattern = Quiz2Info.DATE_PATTERN
            )
        }
}