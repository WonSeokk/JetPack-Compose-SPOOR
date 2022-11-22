package wwon.seokk.abandonedpets.domain

import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicEntity
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity

object FakeData {
    fun getFakePets() = AbandonmentPublicEntity(getPetsEntity())

    private fun getPetsEntity(): List<AbandonmentPublicResultEntity> {
        val petResults: ArrayList<AbandonmentPublicResultEntity> = ArrayList()
        petResults.add(AbandonmentPublicResultEntity(
            "411320202200828",
            "http://www.animal.go.kr/files/shelter/2022/09/202209191509277[1]_s.jpg",
            "20220918",
            "신림동403-11앞도로변",
            "[개] 말티즈",
            "흰",
            "2017(년생)",
            "4.6(Kg)",
            "서울-관악-2022-00215",
            "20220919",
            "20220929",
            "http://www.animal.go.kr/files/shelter/2022/09/202209191509277[1].jpg",
            "종료(반환)",
            "M",
            "Y",
            "치석있고미용한지2달정도되어보이고온순하며잘따르고경계심없으나짖는편임",
            "강현림동물병원",
            "02-2642-9159",
            "서울특별시 양천구 등촌로 160 (목동) 1층",
            "서울특별시 관악구",
            "일자리벤처과",
            "02-879-6691"
        ))
        petResults.add(AbandonmentPublicResultEntity(
            "411316202200845",
            "http://www.animal.go.kr/files/shelter/2022/09/202209191509725_s.jpg",
            "20220918",
            "남구로역 인근",
            "[개] 포메라니안",
            "흰",
            "2021(년생)",
            "2.3(Kg)",
            "서울-구로-2022-00136",
            "20220919",
            "20220929",
            "http://www.animal.go.kr/files/shelter/2022/09/202209191509725.jpg",
            "종료(반환)",
            "F",
            "U",
            "양귀/꼬리끝분홍염색.눈곱.콧물.사람좋아함.발정.칩있음.꼬리단미안됨.털상태양호.",
            "한국동물구조관리협회",
            "031-867-9119",
            "경기도 양주시 남면 감악산로 63-37  ",
            "서울특별시 구로구",
            "질병관리과",
            "02-860-2428"
        ))
        return petResults
    }
}