package com.dani.data

import com.dani.data.repository.CharacterRepository
import com.dani.data.source.LocalDataSource
import com.dani.data.source.RemoteDataSource
import com.dani.domain.Location
import com.dani.domain.Mycharacter
import com.dani.domain.Origin
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import com.dani.data.repository.Result
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var characterRepository: CharacterRepository

    var mockedCharacter = Mycharacter(
        0,
        "",
        "",
        "",
        "",
        "",
        Origin("", ""),
                Location("", ""),
        "",
        emptyList(),
        "",
        ""
    )

    @Before
    fun setUp() {
        localDataSource = mock()
        remoteDataSource = mock()
        characterRepository = CharacterRepository(
            localDataSource,
            remoteDataSource
        )
    }

    @Test
    fun `get all characters from server`() {
        runBlocking {
            val localMycharacterList = mutableListOf(mockedCharacter.copy(1))

            whenever(localDataSource.getAllCharacters()).thenReturn(localMycharacterList)

            val result = characterRepository.getCharactersList(0)

            assertEquals(localMycharacterList, result)
        }
    }

    @Test
    fun `get character info from database with id`() {
        runBlocking {
            val localMycharacter = mockedCharacter.copy(1)

            whenever(localDataSource.getCharacterById(0)).thenReturn(localMycharacter)

            val result = characterRepository.findCharacterById(0)

            assertEquals(localMycharacter, result)
        }
    }

    @Test
    fun `get character info from server with id`() {
        runBlocking {
            val charDemo = mockedCharacter.copy(1)
            val resultCharDemo = Result.Success(charDemo)
            whenever(remoteDataSource.getCharacterInfo(0)).thenReturn(resultCharDemo)

            val result = characterRepository.findCharacterByIdInServer(0)

            assertEquals(charDemo, result)
        }
    }
}
