package com.pjs.soss.ui.login

import com.pjs.soss.data.database.PrefsManager
import com.pjs.soss.data.model.login.DataLogin
import com.pjs.soss.data.model.login.ResponseLogin
import com.pjs.soss.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter (val view : LoginContract.View): LoginContract.Presenter {

    init {
        view.initActivity()
        view.onLoading(false)
    }

    override fun doLogin(username: String, password: String) {
        view.onLoading(true)
        ApiService.endpoint.loginUser(username, password)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {

                }

                override fun onResponse(call: Call<ResponseLogin>,
                                        response: Response<ResponseLogin>
                ) {
                    view.onLoading(false)
                    if (response.isSuccessful) {

                        val responseLogin: ResponseLogin? = response.body()
                        view.showMessage( responseLogin!!.msg )

                            view.onResult( responseLogin!! )


                    }
                }
            })
    }

    override fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin) {
        prefsManager.prefsIsLogin = true
        prefsManager.prefsUsername = dataLogin.username!!
        prefsManager.prefsNamaPegawai = dataLogin.nama_pegawai!!
        prefsManager.prefsJk = dataLogin.jk!!
        prefsManager.prefsAlamat = dataLogin.alamat!!
        prefsManager.prefsIsAktif = dataLogin.is_aktif!!
    }
}