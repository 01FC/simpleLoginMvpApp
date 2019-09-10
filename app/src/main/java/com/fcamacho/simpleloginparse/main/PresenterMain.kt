package com.fcamacho.simpleloginparse.main

import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser

class PresenterMain(private val cView: ContractMain.View) : ContractMain.Presenter {
    override fun logout() {
        ParseUser.logOutInBackground {
            if (it == null)
                cView.exitApp()
            else println("Error: ${it.message}")
        }
    }

    override fun getTopics() {
        val query: ParseQuery<ParseObject> = ParseQuery.getQuery("Topics")
        query.findInBackground { objects, e ->
            if (e == null) {
                cView.bindTopics(objects as ArrayList<ParseObject>)
            } else {
                println("Error: ${e.message}")
            }
        }
    }
}