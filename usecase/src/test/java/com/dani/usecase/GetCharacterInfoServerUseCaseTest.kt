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
class GetCharacterInfoServerUseCaseTest {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var mockedCharacter : Mycharacter
    lateinit var getCharacter: GetCharacterInfoServerUseCase

    @Before
    fun setUp() {
        mockedCharacter = mock()
        getCharacter = GetCharacterInfoServerUseCase(characterRepository)
    }


    @Test
    fun `invoke GetCharacterInfo2UseCase`() {
        runBlocking {
            val localMycharacter = mockedCharacter.copy(1)

            whenever(characterRepository.findCharacterByIdInServer(ArgumentMatchers.anyInt())).thenReturn(localMycharacter)

            val result = getCharacter.invoke(1)

            assertEquals(localMycharacter, result)
        }
    }
}
