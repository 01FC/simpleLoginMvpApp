package com.fcamacho.simpleloginparse.login

interface ContractLogin {
    interface View {
        fun showProgress(show: Boolean)
        fun showError(error: String)
        fun openMainActivity()
    }

    interface Presenter {
        fun logUser(usrname: String, password: String)
    }
}