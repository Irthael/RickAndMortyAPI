package com.dani.rickandmortyapi.di.mycharacters

import com.dani.data.source.LocalDataSource
import com.dani.data.source.RemoteDataSource
import com.dani.rickandmortyapi.model.APIServiceManager
import com.dani.rickandmortyapi.model.database.ApiCharactersDatabase
import com.dani.rickandmortyapi.model.mycharacters.RetrofitDataSource
import com.dani.rickandmortyapi.model.mycharacters.RoomDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CharactersAppModule {

    @Provides
    fun localDataSourceProvider(db: ApiCharactersDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(rickmortyServiceManager: APIServiceManager): RemoteDataSource
            = RetrofitDataSource(rickmortyServiceManager)
}
