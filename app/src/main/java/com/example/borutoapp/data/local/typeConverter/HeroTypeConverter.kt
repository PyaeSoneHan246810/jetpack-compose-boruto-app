package com.example.borutoapp.data.local.typeConverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class HeroTypeConverter {
    private val separator = ","
    @TypeConverter
    fun stringListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        list.forEach { s: String ->
            stringBuilder.append(s).append(separator)
        }
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun stringToStringList(string: String): List<String> {
        return string.split(separator)
    }
}