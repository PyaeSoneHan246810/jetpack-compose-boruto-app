package com.example.borutoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoapp.util.Constants
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.DATABASE_TABLE_HERO)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val day: String,
    val month: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>,
)