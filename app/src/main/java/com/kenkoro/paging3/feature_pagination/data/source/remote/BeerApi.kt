package com.kenkoro.paging3.feature_pagination.data.source.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
  @GET("beers")
  suspend fun getBeers(
    @Query("page") page: Int,
    @Query("per_age") pageCount: Int
  ): List<BeerDto>

  companion object {
    const val BASE_URL = "https://api.punkapi.com/v2/"
  }
}