package com.fcamacho.simpleloginparse.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import com.fcamacho.simpleloginparse.R
import com.fcamacho.simpleloginparse.login.LoginActivity
import com.fcamacho.simpleloginparse.main.MainActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.parse.ParseUser

class SplashActivity : AppCompatActivity(), ContractSplash.View {

    lateinit var mPresenterSplash: ContractSplash.Presenter
    lateinit var mLoadingProgressBar: ContentLoadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenterSplash = PresenterSplash(this)
        mLoadingProgressBar = findViewById(R.id.clpb_splash_loading)
        showProgress(true)
        mPresenterSplash.isOnline()
    }

    override fun showProgress(show: Boolean) {
        if (show) mLoadingProgressBar.visibility = View.VISIBLE
        else mLoadingProgressBar.visibility = View.GONE
    }

    override fun showError(error: String) {
        MaterialAlertDialogBuilder(this).apply {
            setCancelable(false)
            title = "Error"
            setMessage("No se ha podio conectar a internet.\n$error")
            setPositiveButton("Reintentar") { _, _ ->
                mPresenterSplash.isOnline()
            }
            show()
        }
    }

    override fun showLogin() {
        if (ParseUser.getCurrentUser() == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
