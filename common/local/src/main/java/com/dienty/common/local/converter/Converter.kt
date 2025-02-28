package com.dienty.common.local.converter

import androidx.room.TypeConverter
import com.dienty.core.extension.fromJson
import com.dienty.core.extension.toJson

class Converter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}