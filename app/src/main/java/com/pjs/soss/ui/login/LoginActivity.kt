package com.pjs.soss.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pjs.soss.R
import com.pjs.soss.data.database.PrefsManager
import com.pjs.soss.data.model.login.ResponseLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var presenter: LoginPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun initActivity() {
        val username = edtUsername.text
        val password = edtPassword.text

        btnlLogin.setOnClickListener {
            if (username.isNullOrEmpty()) {
                edtUsername.error = "Tidak boleh kosong"
                edtUsername.requestFocus()
            } else if (password.isNullOrEmpty()) {
                edtPassword.error = "Tidak boleh kosong"
                edtPassword.requestFocus()
            } else {
                presenter.doLogin(
                    username.toString(), password.toString()
                )
            }
        }
    }

    override fun initListener() {
        btnlLogin.setOnClickListener {
            presenter.doLogin( edtUsername.text.toString(), edtPassword.text.toString() )
        }

    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnlLogin.visibility = View.GONE
            }
            false -> {
                progress.visibility = View.GONE
                btnlLogin.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseLogin: ResponseLogin) {
        presenter.setPrefs(prefsManager, responseLogin.pegawai!!)
        finish()
    }


    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
