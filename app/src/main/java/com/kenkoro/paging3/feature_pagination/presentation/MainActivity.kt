package com.kenkoro.paging3.feature_pagination.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kenkoro.paging3.feature_pagination.presentation.beer_screen.BeerScreen
import com.kenkoro.paging3.feature_pagination.presentation.beer_screen.BeerViewModel
import com.kenkoro.paging3.ui.theme.Paging3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Paging3Theme {
        Surface(
          modifier = Modifier
            .fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          val beerViewModel = hiltViewModel<BeerViewModel>()
          val beers = beerViewModel.beerPagingFlow.collectAsLazyPagingItems()

          BeerScreen(beers = beers)
        }
      }
    }
  }
}