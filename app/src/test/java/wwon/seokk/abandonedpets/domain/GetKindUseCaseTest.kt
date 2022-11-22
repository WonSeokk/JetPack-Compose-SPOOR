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
import wwon.seokk.abandonedpets.domain.entity.kind.KindEntity
import wwon.seokk.abandonedpets.domain.interatctor.GetKindUseCase
import wwon.seokk.abandonedpets.domain.repository.AbandonedPetsRepository

@ExperimentalCoroutinesApi
@Suppress("NonAsciiCharacters")
class GetKindUseCaseTest {

    private val mockRepository = mockk<AbandonedPetsRepository>()
    private val getKindUseCase = GetKindUseCase(mockRepository)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `Repository 통해 시군구 데이터 가져오기`() = runTest(UnconfinedTestDispatcher()) {
        coEvery { mockRepository.getKind("417000") } answers {
            Record(KindEntity(), null)
        }
        val resultFlow = getKindUseCase.invoke(
            GetKindUseCase.RequestValue("417000")
        )
        resultFlow.test {
            coVerify { mockRepository.getKind("417000") }
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