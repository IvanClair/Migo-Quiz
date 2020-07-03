package personal.ivan.migo_quiz.feature.quiz2.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import personal.ivan.migo_quiz.R
import personal.ivan.migo_quiz.io.model.Quiz2Info
import personal.ivan.migo_quiz.io.model.Quiz2IoModel
import personal.ivan.migo_quiz.io.util.Quiz2IoModelHelper
import personal.ivan.migo_quiz.util.formatDateString

data class Quiz2ListVhBindingModel(
    val pass: String,
    @Quiz2Info.Companion.Quiz2Type val type: Int,
    val value: Int,
    @ColorRes val statusColor: Int,
    @StringRes val status: Int,
    val showActivation: Boolean,
    val activateDateTime: String,
    val dueDateTime: String
) : Parcelable {

    constructor(
        data: Quiz2IoModel,
        util: Quiz2IoModelHelper
    ) : this(
        type = data.info.type,
        pass = data.pass,
        value = data.info.value,
        statusColor = when {
            util.isQuiz2Expired(model = data) -> R.color.colorSecondaryVariant
            util.isActivated(model = data) -> R.color.colorPrimaryVariant
            else -> R.color.colorOnSurface
        },
        status = when {
            util.isQuiz2Expired(model = data) -> R.string.label_expired
            util.isActivated(model = data) -> R.string.label_available
            else -> R.string.label_pending
        },
        showActivation = util.isPending(model = data),
        activateDateTime = data.info.activateDateTime.formatDateString(
            originDatePattern = Quiz2Info.DATE_PATTERN,
            outputDatePattern = DATE_PATTERN
        ),
        dueDateTime = data.info.dueDateTime.formatDateString(
            originDatePattern = Quiz2Info.DATE_PATTERN,
            outputDatePattern = DATE_PATTERN
        )
    )

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pass)
        parcel.writeInt(type)
        parcel.writeInt(value)
        parcel.writeInt(statusColor)
        parcel.writeInt(status)
        parcel.writeByte(if (showActivation) 1 else 0)
        parcel.writeString(activateDateTime)
        parcel.writeString(dueDateTime)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Quiz2ListVhBindingModel> {

        const val DATE_PATTERN = "EEE, dd MMM yyyy, HH:mm"

        override fun createFromParcel(parcel: Parcel): Quiz2ListVhBindingModel =
            Quiz2ListVhBindingModel(parcel)

        override fun newArray(size: Int): Array<Quiz2ListVhBindingModel?> = arrayOfNulls(size)
    }
}