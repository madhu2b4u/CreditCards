package com.example.creditcards.card

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.creditcards.LiveDataTestUtil
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.domain.CardUseCase
import com.example.creditcards.card.presentation.viewmodel.CardViewModel
import com.example.creditcards.common.Result
import com.example.creditcards.common.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


@ExperimentalCoroutinesApi
class CardViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: CardUseCase

    private lateinit var viewModel: CardViewModel

    private val result = listOf(
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

    private val dispatcher = StandardTestDispatcher()


    @Before
    fun init() {
        useCase = mock()
    }

    @Test
    fun testGetSportsLoadingData() {
        CoroutineScope(dispatcher).launch {
            useCase = mock {
                onBlocking { getCards(10) } doReturn liveData {
                    emit(Result.loading())
                }
            }
            viewModel = CardViewModel(useCase)
            viewModel.getCards(10)
            val result = viewModel.result
            result.observeForever { }
            assert(LiveDataTestUtil.getValue(result).peekContent().status == Status.LOADING)
        }
    }

    @Test
    fun testGetSportsListSuccessData() {
        CoroutineScope(dispatcher).launch {

            useCase = mock {
                onBlocking { getCards(10) } doReturn liveData {
                    emit(Result.success(result))
                }
            }

            viewModel = CardViewModel(useCase)
            viewModel.getCards(10)

            val result = viewModel.result
            result.observeForever {}
            assert(
                LiveDataTestUtil.getValue(result).peekContent().status == Status.SUCCESS &&
                        LiveDataTestUtil.getValue(result).peekContent().data == result
            )
        }
    }

    @Test
    fun testGetSportsErrorData() {
        CoroutineScope(dispatcher).launch {
            useCase = mock {
                onBlocking { getCards(10) } doReturn object :
                    LiveData<Result<List<CardDO>>>() {
                    init {
                        value = Result.error("error")
                    }
                }
            }

            viewModel = CardViewModel(useCase)
            viewModel.getCards(10)

            val result = viewModel.result
            result.observeForever {}
            assert(
                LiveDataTestUtil.getValue(result).peekContent().status == Status.ERROR &&
                        LiveDataTestUtil.getValue(result).peekContent().message == "error"
            )
        }

    }

}