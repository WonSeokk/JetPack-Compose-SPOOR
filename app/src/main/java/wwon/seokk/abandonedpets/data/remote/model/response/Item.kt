package wwon.seokk.abandonedpets.data.remote.model.response

import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.entity.kind.KindResultEntity
import wwon.seokk.abandonedpets.domain.entity.region.RegionResultEntity
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterResultEntity

/**
 * Created by WonSeok on 2022.08.02
 **/
data class Item (
    val uprCd: String,
    val orgCd: String,
    val orgdownNm: String,
    val careRegNo: String,
    val careNm: String,
    val kindCd: String,
    val kindNm: String,
    val desertionNo: String,
    val popfile1: String?,
    val happenDt: String,
    val happenPlace: String,
    val colorCd: String,
    val age: String,
    val weight: String,
    val noticeNo: String,
    val noticeSdt: String,
    val noticeEdt: String,
    val popfile2: String?,
    val processState: String,
    val sexCd: String,
    val neuterYn: String,
    val specialMark: String,
    val careTel: String,
    val careAddr: String,
    val orgNm: String,
    val chargeNm: String,
    val officetel: String?,
)

fun Item.toRegionEntity() = RegionResultEntity(uprCd, orgCd, orgdownNm)
fun List<Item>.toRegionsEntity() = map { it.toRegionEntity() }

fun Item.toShelterEntity() = ShelterResultEntity(careRegNo, careNm)
fun List<Item>.toSheltersEntity() = map { it.toShelterEntity() }

fun Item.toKindEntity() = KindResultEntity(kindCd, kindNm)
fun List<Item>.toKindsEntity() = map { it.toKindEntity() }

fun Item.toAbandonmentPublicEntity() = AbandonmentPublicResultEntity(
    desertionNo,
    popfile1 ?: "",
    happenDt,
    happenPlace,
    kindCd,
    colorCd,
    age,
    weight,
    noticeNo,
    noticeSdt,
    noticeEdt,
    popfile2 ?: "",
    processState,
    sexCd,
    neuterYn,
    specialMark,
    careNm,
    careTel,
    careAddr,
    orgNm,
    chargeNm,
    officetel ?: ""
)
fun List<Item>.toAbandonmentPublicEntities() = map { it.toAbandonmentPublicEntity() }
