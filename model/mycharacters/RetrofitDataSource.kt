package com.dani.rickandmortyapi.model.mycharacters

import com.dani.data.repository.Result
import com.dani.data.source.RemoteDataSource
import com.dani.domain.Mycharacter
import com.dani.rickandmortyapi.model.APIServiceManager

class RetrofitDataSource(private val rickandmortyServiceManager: APIServiceManager): RemoteDataSource {

    override suspend fun getCharacterList(page: Int): Result<List<Mycharacter>> {
        return try {
            val itemsResponse = rickandmortyServiceManager.service.getAllCharacters(actualPage = page)
            if (itemsResponse.isSuccessful) {
                Result.Success(itemsResponse.body()?.results?.toList() ?: emptyList())
            } else {
                Result.Error("Error en la respuesta: ${itemsResponse.code()} - ${itemsResponse.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Error en la llamada")
        }
    }

    override suspend fun getCharacterFilterList(page: Int, name: String): Result<List<Mycharacter>> {
        return try {
            val itemsResponse = rickandmortyServiceManager.service.getAllCharactersFilter(actualPage = page, characterName = name)
            return if (itemsResponse.isSuccessful){
                Result.Success(itemsResponse.body()?.results!!.toList())
            }else{
                Result.Error("Sin elementos")
            }
        } catch (e: Exception) {
            Result.Error("Error en la llamada")
        }
    }

    override suspend fun getCharacterInfo(characterID: Int): Result<Mycharacter> {
        return try {
            val itemsResponse = rickandmortyServiceManager.service.getCharactersInfo( characterId = characterID)
            return if (itemsResponse.isSuccessful){
                Result.Success(itemsResponse.body()!!)
            }else{
                Result.Error("Sin elementos")
            }
        } catch (e: Exception) {
            Result.Error("Error en la llamada")
        }
    }
}
