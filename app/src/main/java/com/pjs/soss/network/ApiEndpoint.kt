package com.pjs.soss.network

import com.pjs.soss.data.model.loc.ResponseLocUpdate
import com.pjs.soss.data.model.loc.ResponseLoclist
import com.pjs.soss.data.model.login.ResponseLogin
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login_pegawai")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @GET("agen")
    fun getLoc(): Call<ResponseLoclist>

    @Multipart
    @POST("agen")
    fun insertAgent(
    @Query("nama_toko") nama_toko: String,
    @Query("nama_pemilik") nama_pemilik: String,
    @Query("alamat") alamat: String,
    @Query("latitude") latitude: String,
    @Query("longitude") longitude: String,
    @Part gambar_toko: MultipartBody.Part
    ) : Call<ResponseLocUpdate>
}