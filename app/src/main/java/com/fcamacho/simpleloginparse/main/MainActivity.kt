package com.fcamacho.simpleloginparse.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fcamacho.simpleloginparse.R
import com.fcamacho.simpleloginparse.adapters.AdapterCourses
import com.fcamacho.simpleloginparse.splash.SplashActivity
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.parse.ParseObject

class MainActivity : AppCompatActivity(), ContractMain.View {
    private lateinit var mPresenterMain: ContractMain.Presenter
    private lateinit var mLogoutFab: ExtendedFloatingActionButton
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AdapterCourses

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenterMain = PresenterMain(this)
        mLogoutFab = findViewById(R.id.efab_main_logout)
        mRecyclerView = findViewById(R.id.rv_main_courses_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mPresenterMain.getTopics()
        mLogoutFab.setOnClickListener {
            mPresenterMain.logout()
        }
    }

    override fun bindTopics(topicsList: ArrayList<ParseObject>) {
        mAdapter = if (topicsList.size < 1)
            AdapterCourses(ArrayList())
        else
            AdapterCourses(topicsList)
        mRecyclerView.adapter = mAdapter
    }

    override fun exitApp() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }
}
