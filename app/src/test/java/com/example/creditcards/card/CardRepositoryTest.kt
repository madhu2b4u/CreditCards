package com.example.creditcards.card


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.creditcards.LiveDataTestUtil
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.data.remote.source.CardRemoteDataSource
import com.example.creditcards.card.data.repository.CardRepository
import com.example.creditcards.card.data.repository.CardRepositoryImpl
import com.example.creditcards.common.Status
import com.example.creditcards.database.source.LocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class CardRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repositoryTest: CardRepository

    @Mock
    lateinit var localStore: LocalDataSource
    @Mock
    lateinit var remoteDataStore: CardRemoteDataSource

    private val result = listOf(
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 1, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 2, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 3, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 4, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 5, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 6, "32e06962-4567-452e-9870-8c977baeda20"),
    )

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repositoryTest = CardRepositoryImpl(remoteDataStore,localStore)
    }

    private val dispatcher = StandardTestDispatcher()

    @Test
    fun getCards() {
        CoroutineScope(dispatcher).launch {
            Mockito.`when`(localStore.getCards())
                .thenReturn(result)

            val result = repositoryTest.getCards(10)
            assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
            delay(2500)
            assert(LiveDataTestUtil.getValue(result).status == Status.SUCCESS)
            assert(LiveDataTestUtil.getValue(result).data == result)
        }
    }

    @Test(expected = Exception::class)
    fun getCardsThrowsException() =TestScope().runTest {
        Mockito.doThrow(Exception("no data"))
            .`when`(localStore.getCards())
        repositoryTest.getCards(10)
    }

}