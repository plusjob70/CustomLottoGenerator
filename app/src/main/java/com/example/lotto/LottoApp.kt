package com.example.lotto

import android.app.Application
import android.content.Context

class LottoApp : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: LottoApp? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}