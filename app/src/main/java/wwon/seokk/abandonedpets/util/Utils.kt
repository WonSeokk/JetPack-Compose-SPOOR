package wwon.seokk.abandonedpets.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by WonSeok on 2022.08.15
 **/

fun noticeDateFormatter(noticeSdt: String, noticeEdt: String): String {
    val apiFormatter = DateTimeFormatter.BASIC_ISO_DATE
    val localFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    val sDate= LocalDate.parse(noticeSdt, apiFormatter)
    val eDate= LocalDate.parse(noticeEdt, apiFormatter)
    val reSdt = localFormatter.format(sDate)
    val reEdt = localFormatter.format(eDate)
    return "$reSdt ~ $reEdt"
}