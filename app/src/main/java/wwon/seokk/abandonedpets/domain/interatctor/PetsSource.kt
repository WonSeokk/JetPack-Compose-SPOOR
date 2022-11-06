package wwon.seokk.abandonedpets.domain.interatctor

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import wwon.seokk.abandonedpets.domain.repository.LocalRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.05
 **/
class PetsSource @Inject constructor(
    private val abandonedPetsRepository: AbandonedPetsRepository,
    private val localRepository: LocalRepository,
    private val getAbandonmentPublicRequest: GetAbandonmentPublicRequest
): PagingSource<Int, AbandonmentPublicResultEntity>() {
    companion object {
        const val EMPTY_LIST = "EMPTY_LIST"
    }

    override fun getRefreshKey(state: PagingState<Int, AbandonmentPublicResultEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AbandonmentPublicResultEntity> {
        val nextPage = params.key ?: 1
        getAbandonmentPublicRequest.nextPage = nextPage
        Log.d("PageTEST", "$nextPage")
        val petsResponse = abandonedPetsRepository.getAbandonmentPublic(getAbandonmentPublicRequest)
        return if (petsResponse.data == null) {
            LoadResult.Error(Exception(petsResponse.error.toString()))
        } else {
            if(petsResponse.data.abandonmentPublicEntities.isEmpty())
                LoadResult.Error(Exception(EMPTY_LIST))
            else {
                val likeList = localRepository.getPets().map { it.desertionNo }
                val data = petsResponse.data.abandonmentPublicEntities.map {
                    it.isLike = likeList.contains(it.desertionNo)
                    it
                }
                LoadResult.Page(
                    data = data,
                    prevKey = if (nextPage == 1) null else nextPage-1,
                    nextKey = nextPage.plus(1)
                )
            }

        }
    }
}