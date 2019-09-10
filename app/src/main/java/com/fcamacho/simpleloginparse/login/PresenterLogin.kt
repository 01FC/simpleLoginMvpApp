package com.fcamacho.simpleloginparse.login

import com.parse.ParseUser

class PresenterLogin(val cView:ContractLogin.View):ContractLogin.Presenter {
    override fun logUser(usrname: String, password: String) {

        ParseUser.logInInBackground(usrname,password) { _, e ->
            if (e == null) {
                cView.showProgress(false)
                cView.openMainActivity()
            } else {
                cView.showProgress(false)
                cView.showError(e.message ?: "Error inesperado")
            }
        }
    }
}