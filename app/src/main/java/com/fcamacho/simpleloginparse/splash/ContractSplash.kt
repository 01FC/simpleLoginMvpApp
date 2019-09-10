package com.fcamacho.simpleloginparse.splash

interface ContractSplash {
    interface View {
        fun showProgress(show:Boolean)
        fun showError(error:String)
        fun showLogin()
    }
    interface Presenter {
        fun isOnline()
    }
}