package com.ohyooo.jpsyllabary.app

import android.app.Application

class App : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}