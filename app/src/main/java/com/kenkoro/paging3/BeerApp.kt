package com.kenkoro.paging3

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeerApp : Application(), ImageLoaderFactory {
  override fun newImageLoader(): ImageLoader {
    return ImageLoader(applicationContext).newBuilder()
      .logger(DebugLogger())
      .diskCachePolicy(CachePolicy.ENABLED)
      .diskCache {
        DiskCache.Builder()
          .directory(applicationContext.cacheDir.resolve("image_cache"))
          .build()
      }
      .memoryCachePolicy(CachePolicy.ENABLED)
      .build()
  }
}