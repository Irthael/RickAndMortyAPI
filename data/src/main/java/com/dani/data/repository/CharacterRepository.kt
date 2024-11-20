package com.dani.data.repository

import com.dani.data.source.LocalDataSource
import com.dani.data.source.RemoteDataSource
import com.dani.domain.Mycharacter

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

class CharacterRepository(private val localDataSourceCharacters: LocalDataSource,
                          private val remoteDataSourceCharacters: RemoteDataSource){

    suspend fun getCharactersList(page: Int): List<Mycharacter> {

        when (val result = remoteDataSourceCharacters.getCharacterList(page)) {
            is Result.Success -> {
                val characterList = result.data
                localDataSourceCharacters.saveCharacters(characterList)
            }
            is Result.Error -> {
            }
        }

        val newItems = localDataSourceCharacters.getAllCharacters()
        return newItems
    }

    suspend fun getCharactersListFilter(offset: Int, name: String): List<Mycharacter> {
        when (val result = remoteDataSourceCharacters.getCharacterFilterList(offset, name)) {
            is Result.Success -> {
                val characterList = result.data
                localDataSourceCharacters.saveCharacters(characterList)
            }
            is Result.Error -> {
            }
        }
        return localDataSourceCharacters.getCharactersByName(name)
    }

    suspend fun findCharacterById(characterID: Int): Mycharacter =
        localDataSourceCharacters.getCharacterById(characterID)

    suspend fun findCharacterByIdInServer(characterId: Int): Mycharacter{
        return when (val result = remoteDataSourceCharacters.getCharacterInfo(characterId)) {
            is Result.Success -> {
                result.data
            }

            is Result.Error -> {
                localDataSourceCharacters.getCharacterById(characterId)
            }
        }
    }
}
