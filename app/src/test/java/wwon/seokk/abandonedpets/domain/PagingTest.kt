package wwon.seokk.abandonedpets.domain

import androidx.paging.PagingSource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.base.ErrorRecord
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.interatctor.PetsSource
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository

@Suppress("NonAsciiCharacters")
class PagingTest {

    private val mockRepository = mockk<AbandonedPetsRepository>()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `페이징 로드 성공`() = runBlocking {
        coEvery { mockRepository.getAbandonmentPublic(any()) } returns Record(FakeData.getFakePets(), null)
        val pagingSource = PetsSource(mockRepository, GetAbandonmentPublicRequest.EMPTY)
        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = FakeData.getFakePets().abandonmentPublicEntities,
                prevKey = null,
                nextKey = 2
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `페이징 로드 실패`() = runBlocking {
        coEvery { mockRepository.getAbandonmentPublic(any()) } returns Record(null, ErrorRecord.GenericError)
        val pagingSource = PetsSource(mockRepository, GetAbandonmentPublicRequest.EMPTY)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        Assert.assertTrue(result is PagingSource.LoadResult.Error)
    }

    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }
}