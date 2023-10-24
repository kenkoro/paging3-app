package com.kenkoro.paging3.feature_pagination.data.source.remote

import com.squareup.moshi.Json

data class BeerDto(
  val id: Int,
  val name: String,
  val tagline: String,
  val description: String,
  @Json(name = "first_brewed") val first_brewed: String,
  @Json(name = "image_url") val image_url: String?
)
