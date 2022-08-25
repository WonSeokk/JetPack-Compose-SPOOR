package wwon.seokk.abandonedpets.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

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

fun calculateNoticeDate(noticeEdt: String): String {
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val now = LocalDateTime.now()
    val endDate= LocalDate.parse(noticeEdt, basicFormatter)
    return ChronoUnit.DAYS.between(endDate, now).toString().replace("-","")
}

fun endStateText(text: String) =
    text.replace("종료", "").replace("(","").replace(")","")


fun calculateAge(birth: String): String {
    val basicFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val now = LocalDateTime.now()
    val year = LocalDate.parse(birth.plus("0101"), basicFormatter)
    return ChronoUnit.YEARS.between(year, now).plus(1).toString().replace("-","")
}