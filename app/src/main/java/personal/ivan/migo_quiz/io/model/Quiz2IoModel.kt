package personal.ivan.migo_quiz.io.model

import androidx.annotation.IntDef
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quiz2IoModel(
    @PrimaryKey val pass: String,
    val info: Quiz2Info
)

data class Quiz2Info(
    @Quiz2Type val type: Int,
    val value: Int,
    val activateDateTime: String,
    val dueDateTime: String
) {
    companion object {

        const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ"

        // Type
        @IntDef(
            QUIZ_2_TYPE_DAY,
            QUIZ_2_TYPE_HOUR
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Quiz2Type

        const val QUIZ_2_TYPE_DAY = 0
        const val QUIZ_2_TYPE_HOUR = 1
    }
}