package com.pjs.soss.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pjs.soss.R
import com.pjs.soss.data.database.PrefsManager
import com.pjs.soss.ui.loc.LocActivity
import com.pjs.soss.ui.login.LoginActivity
import com.pjs.soss.ui.sms.SMSActivity
import com.pjs.soss.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var prefManager: PrefsManager
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefManager = PrefsManager(this)
        presenter = MainPresenter(this)

    }

    override fun onStart() {
        super.onStart()
        when (prefManager.prefsIsLogin) {
            true -> {
                crvUser.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
            }
            false -> {
                crvUser.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
            }
        }
    }


    override fun initListener() {

        crvSos.setOnClickListener {
            if (prefManager.prefsIsLogin) {
            startActivity(Intent(this,SMSActivity::class.java))
            } else {
                showMessage("Silahkan Login Dahulu")
            }
        }
            Loc.setOnClickListener {
               if(prefManager.prefsIsLogin) {
            startActivity(Intent(this, LocActivity::class.java))
               } else {
                   showMessage("Silahkan Login Dahulu")
               }
            }
                btnLogin.setOnClickListener {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                crvUser.setOnClickListener {
                    startActivity(Intent(this, UserActivity::class.java))
                }
            }

            override fun showMessage(message: String) {
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }

        }
