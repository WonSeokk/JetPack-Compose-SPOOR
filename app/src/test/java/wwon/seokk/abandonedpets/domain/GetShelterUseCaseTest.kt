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
import wwon.seokk.abandonedpets.domain.entity.shelter.ShelterEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetShelterUseCase
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository

@ExperimentalCoroutinesApi
@Suppress("NonAsciiCharacters")
class GetShelterUseCaseTest {

    private val mockRepository = mockk<AbandonedPetsRepository>()
    private val getShelterUseCase = GetShelterUseCase(mockRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `Repository 통해 시군구 데이터 가져오기`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { mockRepository.getShelter("6110000", "3220000") } answers {
            Record(ShelterEntity(), null)
        }
        val resultFlow = getShelterUseCase.invoke(
            GetShelterUseCase.RequestValue("6110000", "3220000")
        )
        resultFlow.test {
            coVerify { mockRepository.getShelter("6110000", "3220000") }
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