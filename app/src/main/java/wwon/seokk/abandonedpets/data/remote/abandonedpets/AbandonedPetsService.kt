package wwon.seokk.abandonedpets.data.remote.abandonedpets

import retrofit2.http.GET
import retrofit2.http.Query
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse

/**
 * Created by WonSeok on 2022.08.02
 **/
interface AbandonedPetsService {

    @GET("sido_v2")
    suspend fun getSido(): CommonResponse

    @GET("sigungu_v2")
    suspend fun getSigungu(
        @Query("upr_cd") uprCd: String
    ): CommonResponse

    @GET("shelter_v2")
    suspend fun getShelter(
        @Query("upr_cd") uprCd: String,
        @Query("org_cd") orgCd: String
    ): CommonResponse

    @GET("kind_v2")
    suspend fun getKind(
        @Query("up_kind_cd") upKindCd: String
    ): CommonResponse

    @GET("abandonmentPublic_v2")
    suspend fun getAbandonmentPublic(
        @Query("bgnde") bgnde: String,
        @Query("endde") endde: String,
        @Query("upkind") upKind: String,
        @Query("kind") kindCd: String,
        @Query("upr_cd") uprCd: String,
        @Query("org_cd") orgCd: String,
        @Query("care_reg_no") careRegNo: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int
    ): CommonResponse
}
