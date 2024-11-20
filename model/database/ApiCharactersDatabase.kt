package com.dani.rickandmortyapi.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dani.rickandmortyapi.model.database.entities.CharactersEntity
import com.dani.rickandmortyapi.model.database.entities.LocationEntity
import com.dani.rickandmortyapi.model.database.entities.OriginEntity
import com.dani.rickandmortyapi.utils.converter.EpisodeConverter
import com.dani.rickandmortyapi.utils.converter.LocationConverter
import com.dani.rickandmortyapi.utils.converter.OriginConverter

@Database(entities = [ CharactersEntity::class, LocationEntity::class, OriginEntity::class], version = 1, exportSchema = false)
@TypeConverters(OriginConverter::class, LocationConverter::class, EpisodeConverter::class)
abstract class ApiCharactersDatabase : RoomDatabase() {
    abstract fun apiCharactersDao(): ApiCharactersDAO
}
