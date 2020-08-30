package com.pjs.soss.ui.loc

import com.pjs.soss.data.model.loc.ResponseLoclist
import com.pjs.soss.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocPresenter (var view: LocContract.View) : LocContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun getLoc() {
        ApiService.endpoint.getLoc().enqueue(object : Callback<ResponseLoclist> {
            override fun onFailure(call: Call<ResponseLoclist>, t: Throwable) {
                view.onLoadingLoc(false)
            }

            override fun onResponse(
                call: Call<ResponseLoclist>,
                response: Response<ResponseLoclist>
            ) {
                view!!.onLoadingLoc(false)
                if (response.isSuccessful) {
                    val responseLoclist: ResponseLoclist? = response.body()
                    view!!.onResultLoc( responseLoclist!! )
                }
            }
        })
    }
}