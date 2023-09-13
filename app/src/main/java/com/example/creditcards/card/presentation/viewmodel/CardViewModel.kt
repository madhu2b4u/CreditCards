package com.example.creditcards.card.presentation.viewmodel

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.domain.CardUseCase
import com.example.creditcards.common.AppCoroutineDispatcherProvider
import com.example.creditcards.common.AppCoroutineDispatchers
import com.example.creditcards.common.CardUtils
import com.example.creditcards.common.Event
import com.example.creditcards.common.Result
import com.example.creditcards.common.SingleLiveEvent
import com.example.creditcards.common.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class CardViewModel @Inject constructor(
    private val cardUseCase: CardUseCase
) : ViewModel(), DefaultLifecycleObserver {

    val data: LiveData<List<CardDO>> get() = _data
    private val _data = MediatorLiveData<List<CardDO>>()

    val result = MediatorLiveData<Event<Result<List<CardDO>>>>()

    val showLoader: LiveData<Boolean> get() = _showLoader
    private val _showLoader = SingleLiveEvent<Boolean>()

    private val dispatcher: AppCoroutineDispatchers = AppCoroutineDispatcherProvider.dispatcher()

    init {
        getCards(50)
    }

    fun getCards(size: Int) {
        viewModelScope.launch {
            withContext(dispatcher.main()) {
                result.addSource(cardUseCase.getCards(size)) {
                    result.value = Event(it)
                    handleResponse(it)
                }
            }
        }
    }

    private fun handleResponse(result: Result<List<CardDO>>) {
        when (result.status) {
            Status.LOADING -> {
                _showLoader.value = true
            }

            Status.ERROR -> {
                _showLoader.value = false
            }

            Status.SUCCESS -> {
                _showLoader.value = false
                result.data?.let { handleSportsData(it) }
            }
        }
    }

    private fun handleSportsData(data: List<CardDO>) {
        // Sort the list of cards based on the expiration date
        val sortedCards = CardUtils.sortCardsByExpiryDate(data)
        _data.value = sortedCards
    }
}
