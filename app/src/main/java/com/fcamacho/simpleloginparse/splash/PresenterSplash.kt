package com.fcamacho.simpleloginparse.splash

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.URL

class PresenterSplash(val cView: ContractSplash.View) : ContractSplash.Presenter {
    override fun isOnline() {
        doAsync {
            try {
                val requestGET = URL("https://www.google.com").readText()
                uiThread {
                    cView.showProgress(false)
                    cView.showLogin()
                }
            } catch (e:IOException) {
                uiThread {
                    cView.showError(e.message ?: "Error no hay internet")
                }
            }
        }
    }
}