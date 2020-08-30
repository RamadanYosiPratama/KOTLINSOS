package com.pjs.soss.ui.loc.create

import com.pjs.soss.data.model.loc.ResponseLocUpdate
import com.pjs.soss.network.ApiService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class LocCreatePresenter (val view: LocCreateContract.View) : LocCreateContract.Presenter {

    init {
            view.initActivity()
            view.initListener()
            view.onLoading(false)
    }


    override fun insertAgent(
        nama_toko: String,
        nama_pemilik: String,
        alamat: String,
        latitude: String,
        longitude: String,
        gambar_toko: File
    ) {
        val requestBody: RequestBody = RequestBody.create(MediaType.parse("image/*"), gambar_toko)
        val multipartBody: MultipartBody.Part? = MultipartBody.Part.createFormData("gambar_toko",
        gambar_toko.name, requestBody)

        view.onLoading(true)
        ApiService.endpoint.insertAgent( nama_toko, nama_pemilik, alamat, latitude, longitude, multipartBody!! )
            .enqueue(object: Callback<ResponseLocUpdate> {
                override fun onFailure(call: Call<ResponseLocUpdate>, t: Throwable) {
                    view.onLoading(false)
                }

                override fun onResponse(
                    call: Call<ResponseLocUpdate>,
                    response: Response<ResponseLocUpdate>
                ) {
                    view.onLoading(false) 
                    if (response.isSuccessful) {
                        val responseLocUpdate: ResponseLocUpdate? = response.body()
                        view.onResult( responseLocUpdate!!)
                    }
                }

            })
    }

}