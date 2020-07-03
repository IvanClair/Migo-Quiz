package personal.ivan.migo_quiz.util

import android.os.SystemClock

object UiUtil {

    // limit the click event in milli seconds
    private const val MILLI_SECONDS_RESTRICT = 200L

    // use for record last UI clicked unix time
    private var lastUiClickedUnixTime = 0L

    /**
     * Avoid UI double click
     */
    fun allowClick(): Boolean {
        // if the click event is too close to last unix time, than reject it
        if (SystemClock.elapsedRealtime() - lastUiClickedUnixTime < MILLI_SECONDS_RESTRICT) {
            return false
        }
        // save the click unix time
        lastUiClickedUnixTime = SystemClock.elapsedRealtime()
        return true
    }
}