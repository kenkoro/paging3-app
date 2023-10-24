package com.kenkoro.paging3.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerDatabase
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerEntity
import com.kenkoro.paging3.feature_pagination.data.source.remote.BeerApi
import com.kenkoro.paging3.feature_pagination.data.source.remote.BeerRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideBeerDatabase(@ApplicationContext context: Context): BeerDatabase {
    return Room.databaseBuilder(
      context,
      BeerDatabase::class.java,
      BeerDatabase.DB_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun provideBeerApi(): BeerApi {
    return Retrofit.Builder()
      .baseUrl(BeerApi.BASE_URL)
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
      .create()
  }

  @OptIn(ExperimentalPagingApi::class)
  @Provides
  @Singleton
  fun provideBeerPager(beerDb: BeerDatabase, beerApi: BeerApi): Pager<Int, BeerEntity> {
    return Pager(
      config = PagingConfig(pageSize = 20),
      remoteMediator = BeerRemoteMediator(
        beerDb = beerDb,
        beerApi = beerApi
      ),
      pagingSourceFactory = {
        beerDb.dao.pagingSource()
      }
    )
  }
}