package com.pjs.soss.ui.login

import com.pjs.soss.data.database.PrefsManager
import com.pjs.soss.data.model.login.DataLogin
import com.pjs.soss.data.model.login.ResponseLogin

interface LoginContract {

    interface Presenter {
        fun doLogin(username:String, password:String)
        fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }

}