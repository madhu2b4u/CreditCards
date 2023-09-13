package com.example.creditcards.card

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.example.creditcards.LiveDataTestUtil
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.data.repository.CardRepository
import com.example.creditcards.card.domain.CardUseCase
import com.example.creditcards.card.domain.CardUseCaseImpl
import com.example.creditcards.common.Result
import com.example.creditcards.common.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


@ExperimentalCoroutinesApi
class CardUseCaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: CardUseCase

    private lateinit var repository: CardRepository

    private val result = listOf(
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 1, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 2, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 3, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 4, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 5, "32e06962-4567-452e-9870-8c977baeda20"),
        CardDO("2027-09-12", "1234-2121-1221-1211", "forbrugsforeningen", 6, "32e06962-4567-452e-9870-8c977baeda20"),
    )

    private val dispatcher = StandardTestDispatcher()

    @Test
    fun testCardsRequestLoading() {
        CoroutineScope(dispatcher).launch {
            repository = mock {
                onBlocking {
                    getCards(10)
                } doReturn liveData {
                    emit(Result.loading())
                }
            }

            useCase = CardUseCaseImpl(repository)

            val result = useCase.getCards(10)

            assert(LiveDataTestUtil.getValue(result).status == Status.LOADING)
        }
    }

    @Test
    fun testCardsRequestSuccess() {
        CoroutineScope(dispatcher).launch {
            repository = mock {
                onBlocking {
                    getCards(10)
                } doReturn liveData {
                    emit(Result.success(result))
                }
            }

            useCase = CardUseCaseImpl(repository)

            val result = useCase.getCards(10)

            assert(
                LiveDataTestUtil.getValue(result).data == result &&
                        LiveDataTestUtil.getValue(result).status == Status.SUCCESS
            )
        }
    }

    @Test
    fun testCardsRequestErrorData() {
        CoroutineScope(dispatcher).launch {
            repository = mock {
                onBlocking { getCards(10) } doReturn liveData {
                    emit(Result.error("no data"))
                }
            }
            useCase = CardUseCaseImpl(repository)

            val result = useCase.getCards(10)
            result.observeForever { }
            assert(
                LiveDataTestUtil.getValue(result).status == Status.ERROR && LiveDataTestUtil.getValue(
                    result
                ).message == "no data"
            )
        }

    }

}