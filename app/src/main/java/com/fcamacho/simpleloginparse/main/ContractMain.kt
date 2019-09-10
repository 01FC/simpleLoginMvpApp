package com.fcamacho.simpleloginparse.main

import com.parse.ParseObject

interface ContractMain {
    interface View {
        fun bindTopics(topicsList:ArrayList<ParseObject>)
        fun exitApp()
    }
    interface Presenter {
        fun getTopics()
        fun logout()
    }
}