package wwon.seokk.abandonedpets.util

import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import wwon.seokk.abandonedpets.ui.calendar.CALENDAR_STARTS_ON
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields

/**
 * Created by WonSeok on 2022.08.15
 **/

fun noticeDateFormatter(noticeSdt: String, noticeEdt: String): String {
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val localFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    val sDate= LocalDate.parse(noticeSdt, basicFormatter)
    val eDate= LocalDate.parse(noticeEdt, basicFormatter)
    val reSdt = localFormatter.format(sDate)
    val reEdt = localFormatter.format(eDate)
    return "$reSdt ~ $reEdt"
}

fun LocalDate.toFormat(formatter: DateTimeFormatter = DateTimeFormatter.BASIC_ISO_DATE): String {
    return formatter.format(this) ?: formatter.format(LocalDate.now())
}

fun String.toFormat(): LocalDate {
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    return LocalDate.parse(this, basicFormatter)
}

fun calculateNoticeDate(noticeEdt: String): String {
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val now = LocalDateTime.now()
    val endDate= LocalDate.parse(noticeEdt, basicFormatter)
        return ChronoUnit.DAYS.between(endDate, now).toString().replace("-","")
}

fun endStateText(text: String) =
    text.replace("종료", "").replace("(","").replace(")","")


fun calculateAge(birth: String): String {
    val year = birth.ifBlank { LocalDate.now().year.toString() }
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val now = LocalDateTime.now()
    val compareDate = LocalDate.parse("${year}0101", basicFormatter)
    return ChronoUnit.YEARS.between(compareDate, now).plus(1).toString().replace("-","")
}

fun YearMonth.getNumberWeeks(weekFields: WeekFields = CALENDAR_STARTS_ON): Int {
    val firstWeekNumber = this.atDay(1)[weekFields.weekOfMonth()]
    val lastWeekNumber = this.atEndOfMonth()[weekFields.weekOfMonth()]
    return lastWeekNumber - firstWeekNumber + 1 // Both weeks inclusive
}

fun SystemUiController.setStatusBar(darkMode: Boolean) {
    setStatusBarColor(
        darkIcons = darkMode,
        color = Color.Transparent
    )
}