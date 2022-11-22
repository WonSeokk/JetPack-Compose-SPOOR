package wwon.seokk.abandonedpets.domain

import app.cash.turbine.test
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import wwon.seokk.abandonedpets.domain.entity.base.Record
import wwon.seokk.abandonedpets.domain.entity.region.RegionEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetSidoUseCase
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository

@ExperimentalCoroutinesApi
@Suppress("NonAsciiCharacters")
class GetSidoUseCaseTest {

    private val mockRepository = mockk<AbandonedPetsRepository>()
    private val getSidoUseCase = GetSidoUseCase(mockRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `Repository 통해 시도 데이터 가져오기`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { mockRepository.getSido() } answers {
            Record(RegionEntity(), null)
        }
        val resultFlow = getSidoUseCase.invoke(null)
        resultFlow.test {
            coVerify { mockRepository.getSido() }
            confirmVerified(mockRepository)

            val result = awaitItem()
            Assert.assertTrue(result.data != null)
            Assert.assertTrue(result.error == null)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
        unmockkAll()
    }
}