package wwon.seokk.abandonedpets.domain.entity.abandonmentpublic

/**
 * Created by WonSeok on 2022.08.02
 **/
data class AbandonmentPublicResultEntity (
    val desertionNo: String,
    val filename: String,
    val happenDt: String,
    val happenPlace: String,
    val kindCd: String,
    val colorCd: String,
    val age: String,
    val weight: String,
    val noticeNo: String,
    val noticeSdt: String,
    val noticeEdt: String,
    val popfile: String,
    val processState: String,
    val sexCd: String,
    val neuterYn: String,
    val specialMark: String,
    val careNm: String,
    val careTel: String,
    val careAddr: String,
    val orgNm: String,
    val chargeNm: String?,
    val officetel: String
)