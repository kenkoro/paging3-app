package com.kenkoro.paging3.feature_pagination.data.source.mappers

import com.kenkoro.paging3.feature_pagination.data.source.remote.BeerDto
import com.kenkoro.paging3.feature_pagination.domain.model.Beer
import com.kenkoro.paging3.feature_pagination.data.source.local.BeerEntity

fun BeerDto.toBeerEntity(): BeerEntity {
  return BeerEntity(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    firstBrewed = first_brewed,
    imageUrl = image_url
  )
}

fun BeerEntity.toBeer(): Beer {
  return Beer(
    id = id,
    name = name,
    tagline = tagline,
    description = description,
    firstBrewed = firstBrewed,
    imageUrl = imageUrl
  )
}