package com.dani.data.source

import com.dani.domain.Mycharacter

interface LocalDataSource {
    suspend fun getAllCharacters(): List<Mycharacter>
    suspend fun saveCharacters(list: List<Mycharacter>)
    suspend fun getCharactersByName(name: String): List<Mycharacter>
    suspend fun getCharacterById(characterID: Int): Mycharacter
}


