package personal.ivan.migo_quiz

import org.junit.Assert
import org.junit.Test
import personal.ivan.migo_quiz.util.formatDateString
import java.util.*

class DateUtilTest {

    // Properties for Arrangement
    private val originDateString = "2020-06-01T23:48:00+1000"
    private val originDatePattern = "yyyy-MM-dd'T'HH:mm:ssZ"
    private val originDateLocale = Locale.ENGLISH
    private val outputDateString = "Mon, 01 Jun 2020, 23:48"
    private val outputDatePattern = "EEE, dd MMM yyyy, HH:mm"
    private val outputDateLocale = Locale.ENGLISH

    // region formatDateString

    /**
     * Normal case - date string contains timezone
     */
    @Test
    fun formatDateString_shouldWorkAsExpected_underNormalCase() {
        val expected = outputDateString
        val actual = originDateString.formatDateString(
            originDatePattern = originDatePattern,
            originDateLocale = originDateLocale,
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)
    }

    /**
     * Normal case - date string does not contains timezone
     */
    @Test
    fun formatDateString_shouldWorkAsExpected_evenDateStringDoesNotContainsTimezone() {
        val expected = outputDateString
        val actual = "2020-06-01T23:48:00".formatDateString(
            originDatePattern = "yyyy-MM-dd'T'HH:mm:ss",
            originDateLocale = originDateLocale,
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)
    }

    /**
     * Since default origin locale of [formatDateString] is english,
     * if you are sure that the locale of date string is english,
     * you do not need to pass it to the function
     */
    @Test
    fun formatDateString_shouldWorkAsExpected_whenOriginDateLocaleIsEnglish() {
        val expected = outputDateString
        val actual = originDateString.formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)
    }

    /**
     * Since default output locale of [formatDateString] is device locale,
     * so you do not need to pass it if you want the output using device locale
     */
    @Test
    fun formatDateString_shouldWorkAsExpected_whenOutputLocaleIsDeviceLocale() {
        val expected = originDateString.formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = outputDatePattern,
            outputDateLocale = Locale.getDefault()
        )
        val actual = originDateString.formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = outputDatePattern
        )
        Assert.assertEquals(expected, actual)
    }

    /**
     * When contains issued parameters, it will return empty string
     */
    @Test
    fun formatDateString_shouldReturnEmptyString_whenExceptionHappened() {
        val expected = ""

        // issued date string
        var actual = "originDateString".formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)

        // null date string
        actual = null.formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)

        // issued origin date pattern
        actual = originDateString.formatDateString(
            originDatePattern = "originDatePattern",
            outputDatePattern = outputDatePattern,
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)

        // issued output date pattern
        actual = originDateString.formatDateString(
            originDatePattern = originDatePattern,
            outputDatePattern = "outputDatePattern",
            outputDateLocale = outputDateLocale
        )
        Assert.assertEquals(expected, actual)
    }

    // endregion
}