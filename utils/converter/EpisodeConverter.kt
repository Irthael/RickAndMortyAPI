package com.dani.rickandmortyapi.utils.converter

import androidx.room.TypeConverter
import com.dani.domain.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Room [TypeConverter] for [List]
 */
class EpisodeConverter {

    @TypeConverter
    fun episodeListToString(list: List<String>): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToEpisodeList(stringList: String?): List<String>? {
        return if (stringList == null) null
        else Gson().fromJson(stringList, object : TypeToken<List<String>>() {}.type)
    }
}
