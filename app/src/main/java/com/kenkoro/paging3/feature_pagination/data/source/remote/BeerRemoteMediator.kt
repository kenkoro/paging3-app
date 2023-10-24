package com.kenkoro.paging3.feature_pagination.data.source.remote

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerDatabase
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerEntity
import com.kenkoro.paging3.feature_pagination.data.source.mappers.toBeerEntity
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeerRemoteMediator(
  private val beerDb: BeerDatabase,
  private val beerApi: BeerApi
) : RemoteMediator<Int, BeerEntity>() {
  @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, BeerEntity>
  ): MediatorResult {
    return try {
      /**
       * WARN: LoadType.PREPEND is not supported
       */
      val loadKey = when (loadType) {
        LoadType.REFRESH -> 1
        LoadType.PREPEND -> return MediatorResult.Success(
          endOfPaginationReached = true
        )
        LoadType.APPEND -> {
          val lastItem = state.lastItemOrNull()
          if (lastItem == null) {
            1
          } else {
            (lastItem.id / state.config.pageSize) + 1
          }
        }
      }

      val beers = beerApi.getBeers(loadKey, state.config.pageSize)

      beerDb.withTransaction {
        if (loadType == LoadType.REFRESH) {
          beerDb.dao.clearAll()
        }
        val beerEntities = beers.map { beerDto ->
          beerDto.toBeerEntity()
        }
        beerDb.dao.upsertAll(beerEntities)
      }

      MediatorResult.Success(endOfPaginationReached = beers.isEmpty())
    } catch (e: IOException) {
      MediatorResult.Error(e)
    } catch (e: HttpException) {
      MediatorResult.Error(e)
    }
  }
}