package com.dani.rickandmortyapi.model.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dani.domain.*
import com.dani.rickandmortyapi.utils.converter.EpisodeConverter
import java.util.Date

@Entity
data class CharactersEntity(
    @PrimaryKey (autoGenerate = false)
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    @TypeConverters(EpisodeConverter::class)
    val episode: List<String>,
    val url: String,
    val created: String
)
