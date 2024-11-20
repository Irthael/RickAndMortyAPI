package com.dani.rickandmortyapi.model.mycharacters

import com.dani.data.source.LocalDataSource
import com.dani.rickandmortyapi.model.database.ApiCharactersDatabase
import com.dani.rickandmortyapi.model.database.entities.CharactersEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.dani.domain.Mycharacter as CharactersDomain

class RoomDataSource(database: ApiCharactersDatabase) : LocalDataSource {

    private val dao = database.apiCharactersDao()

    override suspend fun saveCharacters(list: List<CharactersDomain>) = withContext(Dispatchers.IO) {
        dao.insertCharacter(list.map(CharactersDomain::toCharactersEntity))
    }

    override suspend fun getAllCharacters(): List<CharactersDomain> = withContext(Dispatchers.IO){
        dao.getAllCharacters().map(CharactersEntity::toCharactersDomain)
    }

    override suspend fun getCharactersByName(name: String): List<CharactersDomain> = withContext(Dispatchers.IO){
        dao.getAllCharactersByName(name).map(CharactersEntity::toCharactersDomain)
    }

    override suspend fun getCharacterById(characterID: Int) = withContext(Dispatchers.IO) {
        dao.getCharactersById(characterID).toCharactersDomain()}

}
