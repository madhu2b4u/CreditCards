package com.example.creditcards.card

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.data.remote.services.CardService
import com.example.creditcards.card.data.remote.source.CardRemoteDataSource
import com.example.creditcards.card.data.remote.source.CardRemoteDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import retrofit2.Response

@ExperimentalCoroutinesApi
class CardRemoteDataSourceTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    private lateinit var remoteDataSource: CardRemoteDataSource


    private lateinit var service: CardService

    private val coroutineContext = Dispatchers.Default

    private val dispatcher = StandardTestDispatcher()


    private val mockCardDoList = listOf(
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            1,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            2,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            3,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            4,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            5,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
        CardDO(
            "2027-09-12",
            "1234-2121-1221-1211",
            "forbrugsforeningen",
            6,
            "32e06962-4567-452e-9870-8c977baeda20"
        ),
    )


    @Before
    fun init() {
        service = mock {
            onBlocking { getCardAsync(10) } doReturn GlobalScope.async {
                Response.success(mockCardDoList)
            }
        }

        remoteDataSource = CardRemoteDataSourceImpl(service, coroutineContext)

    }

    @Test
    fun testGetCard() {
        CoroutineScope(dispatcher).launch {
            service = mock {
                onBlocking { getCardAsync(10) } doReturn GlobalScope.async {
                    Response.success(mockCardDoList)
                }
            }

            remoteDataSource =
                CardRemoteDataSourceImpl(service, coroutineContext)

            val result = remoteDataSource.getCards(10)

            assert(result == mockCardDoList)
        }
    }

    @Test(expected = Exception::class)
    fun testThrowGetCardException() = TestScope().runTest {
        service = mock {
            onBlocking { getCardAsync(10) } doReturn GlobalScope.async {
                Response.error(404, null)
            }
        }

        remoteDataSource =
            CardRemoteDataSourceImpl(service, coroutineContext)

        assert(remoteDataSource.getCards(10) == mockCardDoList)
    }


}