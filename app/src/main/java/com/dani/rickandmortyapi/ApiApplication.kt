package com.dani.rickandmortyapi

import android.app.Application
import com.dani.rickandmortyapi.di.mycharacters.CharactersComponent
import com.dani.rickandmortyapi.di.mycharacters.DaggerCharactersComponent

open class ApiApplication : Application() {

    lateinit var charactersComponent: CharactersComponent
        private set

    override fun onCreate() {
        super.onCreate()
        charactersComponent = DaggerCharactersComponent.factory().create(this)
    }
}
