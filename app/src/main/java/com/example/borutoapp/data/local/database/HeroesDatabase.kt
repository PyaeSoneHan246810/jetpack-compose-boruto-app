package com.example.borutoapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.example.borutoapp.data.local.typeConverter.HeroTypeConverter
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1, exportSchema = false)
@TypeConverters(HeroTypeConverter::class)
abstract class HeroesDatabase: RoomDatabase() {
    abstract val heroDao: HeroDao
    abstract val heroRemoteKeyDao: HeroRemoteKeyDao
}