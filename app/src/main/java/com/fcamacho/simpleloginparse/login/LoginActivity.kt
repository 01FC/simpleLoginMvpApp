package com.fcamacho.simpleloginparse.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import com.fcamacho.simpleloginparse.R
import com.fcamacho.simpleloginparse.main.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), ContractLogin.View {

    lateinit var mPresenterLogin: ContractLogin.Presenter
    lateinit var mLoading: ContentLoadingProgressBar
    lateinit var mImageLogo: ImageView
    lateinit var mTextLayoutUsername: TextInputLayout
    lateinit var mTextLayoutPassword: TextInputLayout
    lateinit var mTextEditUsername: TextInputEditText
    lateinit var mTextEditPasword: TextInputEditText
    lateinit var mLoginButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenterLogin = PresenterLogin(this)
        mLoading = findViewById(R.id.clpb_login_loading)
        mImageLogo = findViewById(R.id.iv_login_logo)
        mTextLayoutUsername = findViewById(R.id.til_login_username)
        mTextLayoutPassword = findViewById(R.id.til_login_password)
        mTextEditUsername = findViewById(R.id.tiet_login_username)
        mTextEditPasword = findViewById(R.id.tiet_login_password)
        mLoginButton = findViewById(R.id.mbtn_login_log_in)

        mLoginButton.setOnClickListener {
            verifyData()
        }
    }

    private fun verifyData() {
        mTextLayoutUsername.error = null
        mTextLayoutPassword.error = null
        val user = mTextEditUsername.text.toString()
        val pass = mTextEditPasword.text.toString()
        when {
            TextUtils.isEmpty(user) -> mTextLayoutUsername.error = "Ingrese usuario"
            TextUtils.isEmpty(pass) -> mTextLayoutPassword.error = "Ingrese password"
            else -> mPresenterLogin.logUser(user, pass)
        }
    }

    override fun showProgress(show: Boolean) {
        if (show) mLoading.visibility = View.VISIBLE
        else mLoading.visibility = View.GONE
    }

    override fun showError(error: String) {
        Snackbar.make(fl_login_layout, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
