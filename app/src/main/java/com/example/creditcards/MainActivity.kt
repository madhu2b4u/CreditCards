package com.example.creditcards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.creditcards.card.data.models.CardDO
import com.example.creditcards.card.presentation.ui.views.CardItem
import com.example.creditcards.card.presentation.viewmodel.CardViewModel
import com.example.creditcards.ui.theme.CreditCardsTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreditCardsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetCards(viewModel)
                }
            }
        }
    }
}

@Composable
fun GetCards(viewModel: CardViewModel) {

    val dataState: LiveData<List<CardDO>> = viewModel.data // Your LiveData here

    val showLoaderState: LiveData<Boolean> = viewModel.showLoader // Your LiveData here

    val observedDataState: List<CardDO>? by dataState.observeAsState(null)

    val observedShowLoaderState: Boolean? by showLoaderState.observeAsState(false)

    val resultState by viewModel.result.observeAsState()

    if (observedShowLoaderState == false) {
        observedDataState?.let { creditCards ->
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(
                    creditCards
                ) {
                    CardItem(card = it)
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
