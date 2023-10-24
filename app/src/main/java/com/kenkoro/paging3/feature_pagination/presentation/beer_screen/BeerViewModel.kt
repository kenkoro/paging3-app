package com.kenkoro.paging3.feature_pagination.presentation.beer_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerEntity
import com.kenkoro.paging3.feature_pagination.data.source.mappers.toBeer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
  pager: Pager<Int, BeerEntity>
) : ViewModel() {
  val beerPagingFlow = pager
    .flow
    .map { pagingData ->
      pagingData.map { it.toBeer() }
    }
    .cachedIn(viewModelScope)
}