package com.kenkoro.paging3.feature_pagination.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = [BeerEntity::class],
  version = 1
)
abstract class BeerDatabase : RoomDatabase() {
  abstract val dao: BeerDao

  companion object {
    const val DB_NAME = "beers.db"
  }
}