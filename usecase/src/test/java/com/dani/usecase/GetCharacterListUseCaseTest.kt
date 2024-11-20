package com.dani.usecase

import com.dani.data.repository.CharacterRepository
import com.dani.domain.Mycharacter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCharacterListUseCaseTest {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var mockedCharacter : Mycharacter
    lateinit var getCharacter: GetCharacterListUseCase

    @Before
    fun setUp() {
        mockedCharacter = mock()
        getCharacter = GetCharacterListUseCase(characterRepository)
    }


    @Test
    fun `invoke GetCharacterInfoUseCase`() {
        runBlocking {
            val localMycharacter = mutableListOf(mockedCharacter.copy(1))

            whenever(characterRepository.getCharactersList(ArgumentMatchers.anyInt())).thenReturn(localMycharacter)

            val result = getCharacter.getNormalList(0)

            assertEquals(localMycharacter, result)
        }
    }
}