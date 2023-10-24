package com.kenkoro.paging3.feature_pagination.presentation.beer_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.kenkoro.paging3.feature_pagination.domain.model.Beer
import com.kenkoro.paging3.feature_pagination.presentation.beer_screen.components.BeerItem

@Composable
fun BeerScreen(beers: LazyPagingItems<Beer>) {
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(beers.loadState) {
    if (beers.loadState.refresh is LoadState.Error) {
      snackbarHostState.showSnackbar(
        message = "Couldn't refresh the current page",
        withDismissAction = true,
        duration = SnackbarDuration.Long
      )
    }

    if (beers.loadState.append is LoadState.Error) {
      snackbarHostState.showSnackbar(
        message = "Couldn't load new beers",
        withDismissAction = true,
        duration = SnackbarDuration.Long
      )
    }
  }

  Scaffold(
    modifier = Modifier.padding(16.dp),
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
      if (beers.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator(
          modifier = Modifier.align(Alignment.Center)
        )
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.spacedBy(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          items(
            count = beers.itemCount,
            key = beers.itemKey { it.id },
          ) { index ->
            val beer = beers[index]
            if (beer != null) {
              BeerItem(
                beer = beer,
                modifier = Modifier.fillMaxWidth()
              )
            }
          }
          item {
            if (beers.loadState.append is LoadState.Loading) {
              CircularProgressIndicator()
            }
          }
        }
      }
    }
  }
}