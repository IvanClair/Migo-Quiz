package personal.ivan.migo_quiz.io.db

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import personal.ivan.migo_quiz.io.model.Quiz2Info

object DbTypeConverter {

    // region Convert functions

    @JvmStatic
    @TypeConverter
    fun quiz2InfoToString(data: Quiz2Info): String =
        toJson(
            data = data,
            cls = Quiz2Info::class.java
        )

    @JvmStatic
    @TypeConverter
    fun stringToQuiz2Info(json: String): Quiz2Info? =
        fromJson(json, Quiz2Info::class.java)

    // endregion

    // region Moshi functions for convert JSON

    @ToJson
    private inline fun <reified T> toJson(
        data: T,
        cls: Class<T>
    ): String =
        Moshi
            .Builder()
            .build()
            .adapter(cls)
            .toJson(data)

    @FromJson
    private inline fun <reified T> fromJson(
        data: String,
        cls: Class<T>
    ): T? =
        Moshi
            .Builder()
            .build()
            .adapter(cls)
            .fromJson(data)

    // endregion
}