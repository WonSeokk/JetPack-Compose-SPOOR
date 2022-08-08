package wwon.seokk.abandonedpets.domain.interatctor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository
import javax.inject.Inject

/**
 * Created by WonSeok on 2022.08.05
 **/
class PetsSource @Inject constructor(
    private val abandonedPetsRepository: AbandonedPetsRepository,
    private val getAbandonmentPublicRequest: GetAbandonmentPublicRequest
): PagingSource<Int, AbandonmentPublicResultEntity>() {

    override fun getRefreshKey(state: PagingState<Int, AbandonmentPublicResultEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AbandonmentPublicResultEntity> {
        val nextPage = params.key ?: 1
        getAbandonmentPublicRequest.nextPage = nextPage
        val petsResponse = abandonedPetsRepository.getAbandonmentPublic(getAbandonmentPublicRequest)
        return if (petsResponse.data == null) {
            LoadResult.Error(Exception(petsResponse.error.toString()))
        } else {
            LoadResult.Page(
                data = petsResponse.data.abandonmentPublicEntities,
                prevKey = if (nextPage == 1) null else nextPage-1,
                nextKey = nextPage.plus(1)
            )
        }
    }
}