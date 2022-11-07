package wwon.seokk.abandonedpets.domain.entity.abandonmentpublic

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import wwon.seokk.abandonedpets.data.local.entities.Pet
import wwon.seokk.abandonedpets.util.toFormat
import java.time.LocalDate

/**
 * Created by WonSeok on 2022.08.02
 **/
@Parcelize
data class AbandonmentPublicResultEntity (
    val desertionNo: String = "",
    val filename: String = "",
    val happenDt: String = LocalDate.now().toFormat(),
    val happenPlace: String = "",
    val kindCd: String = "",
    val colorCd: String = "",
    val age: String = "",
    val weight: String = "",
    val noticeNo: String = "",
    val noticeSdt: String = LocalDate.now().toFormat(),
    val noticeEdt: String = LocalDate.now().toFormat(),
    val popfile: String = "",
    val processState: String = "",
    val sexCd: String = "",
    val neuterYn: String = "",
    val specialMark: String = "",
    val careNm: String = "",
    val careTel: String = "",
    val careAddr: String = "",
    val orgNm: String = "",
    val chargeNm: String? = null,
    val officetel: String = ""
): Parcelable {
    companion object {
        val EMPTY = AbandonmentPublicResultEntity()
    }
}

fun AbandonmentPublicResultEntity.toPet() = Pet(
    desertionNo,
    filename,
    happenDt,
    happenPlace,
    kindCd,
    colorCd,
    age,
    weight,
    noticeNo,
    noticeSdt,
    noticeEdt,
    popfile,
    processState,
    sexCd,
    neuterYn,
    specialMark,
    careNm,
    careTel,
    careAddr,
    orgNm,
    chargeNm,
    officetel
)