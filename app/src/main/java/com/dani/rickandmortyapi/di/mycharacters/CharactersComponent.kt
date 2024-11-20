package com.dani.rickandmortyapi.di.mycharacters

import android.app.Application
import com.dani.rickandmortyapi.di.DataSourcesModule
import com.dani.rickandmortyapi.ui.detail.CharactersDetailComponent
import com.dani.rickandmortyapi.ui.detail.CharactersDetailModule
import com.dani.rickandmortyapi.ui.list.CharactersListActivityComponent
import com.dani.rickandmortyapi.ui.list.CharactersListActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CharactersAppModule::class,
    CharactersDataModule::class,
    CharactersUseCaseModule::class,
    DataSourcesModule::class
])

interface CharactersComponent {

    fun plus(charactersListActivityModule: CharactersListActivityModule): CharactersListActivityComponent
    fun plus(charactersDetail2ActivityModule: CharactersDetailModule): CharactersDetailComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): CharactersComponent
    }
}
