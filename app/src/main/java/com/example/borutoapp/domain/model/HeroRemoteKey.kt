package com.example.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoapp.util.Constants

@Entity(tableName = Constants.DATABASE_TABLE_HERO_REMOTE_KEY)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated: Long?
)
