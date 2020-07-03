@file:JvmName("DateUtil")

package personal.ivan.migo_quiz.util

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// region Format date string to expected pattern

/**
 * Format date String to expected pattern [outputDatePattern] and language [outputDateLocale]
 *
 * @param originDatePattern origin date pattern (e.g. yyyy-MM-dd)
 * @param originDateLocale  locale of date string, default will be english
 * @param outputDatePattern expected output date pattern (e.g. yyyy/MM/dd)
 * @param outputDateLocale  expected output language, default will be device locale
 *
 * @return empty string if any exception
 */
fun String?.formatDateString(
    originDatePattern: String,
    originDateLocale: Locale = Locale.ENGLISH,
    outputDatePattern: String,
    outputDateLocale: Locale = Locale.getDefault()
): String =
    try {
        val ldt = getLdt(
            dateString = this,
            datePattern = originDatePattern,
            locale = originDateLocale
        )
        getFormatter(datePattern = outputDatePattern, locale = outputDateLocale).format(ldt)
    } catch (e: Exception) {
        ""
    }

/**
 * Get current zoned date string
 */
fun getCurrentZonedDateString(
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): String =
    try {
        DateTimeFormatter
            .ofPattern(datePattern, locale)
            .format(ZonedDateTime.now())
    } catch (e: Exception) {
        ""
    }

/**
 * Plus days to the date string
 */
fun String?.plusDay(
    day: Int,
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): String =
    try {
        val zonedDt =
            getFormattedZonedDt(dateString = this, datePattern = datePattern, locale = locale)
                .plusDays(day.toLong())
                .with(LocalTime.of(23, 59, 59))
        getFormatter(datePattern = datePattern, locale = locale).format(zonedDt)
    } catch (e: Exception) {
        ""
    }

/**
 * Plus hours to the date string
 */
fun String?.plusHour(
    hour: Int,
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): String =
    try {
        val zonedDt =
            getFormattedZonedDt(dateString = this, datePattern = datePattern, locale = locale)
                .plusHours(hour.toLong())
        val adjustZonedDt =
            zonedDt.with(LocalTime.of(zonedDt.hour, 59, 59))
        getFormatter(datePattern = datePattern, locale = locale).format(adjustZonedDt)
    } catch (e: Exception) {
        ""
    }

/**
 * Compare target date string with now
 */
fun String?.isAfterNow(
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): Boolean =
    try {
        val targetZonedDt =
            getFormattedZonedDt(dateString = this, datePattern = datePattern, locale = locale)
        val nowZonedLdt = ZonedDateTime.of(LocalDateTime.now(), targetZonedDt.zone)
        nowZonedLdt.isAfter(targetZonedDt)
    } catch (e: Exception) {
        false
    }

// endregion

// region Private functions

/**
 * Get [LocalDateTime] base on parameters
 */
private fun getLdt(
    dateString: String?,
    datePattern: String,
    locale: Locale
): LocalDateTime = LocalDateTime.parse(
    dateString,
    DateTimeFormatter.ofPattern(datePattern, locale)
)

/**
 * Get [DateTimeFormatter] by params
 */
private fun getFormatter(
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): DateTimeFormatter =
    DateTimeFormatter.ofPattern(datePattern, locale)

/**
 * Get formatted [ZonedDateTime]
 */
private fun getFormattedZonedDt(
    dateString: String?,
    datePattern: String,
    locale: Locale = Locale.ENGLISH
): ZonedDateTime = ZonedDateTime.parse(
    dateString,
    getFormatter(datePattern = datePattern, locale = locale)
)

// endregion
