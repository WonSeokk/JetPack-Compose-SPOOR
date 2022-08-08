package wwon.seokk.abandonedpets.data.remote.abandonedpets

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import wwon.seokk.abandonedpets.data.remote.model.response.common.CommonResponse

/**
 * Created by WonSeok on 2022.08.02
 **/
interface AbandonedPetsService {

    @GET("/sido")
    suspend fun getSido(): CommonResponse

    @GET("/sigungu")
    suspend fun getSigungu(
        @Query("upr_cd") uprCd: String
    ): CommonResponse

    @GET("/shelter")
    suspend fun getShelter(
        @Query("upr_cd") uprCd: String,
        @Query("org_cd") orgCd: String
    ): CommonResponse

    @GET("/kind")
    suspend fun getKind(
        @Query("up_kind_cd") upKindCd: String
    ): CommonResponse

    @GET("abandonmentPublic")
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