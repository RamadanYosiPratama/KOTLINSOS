package com.pjs.soss.data.model.login

import com.google.gson.annotations.SerializedName

data class DataLogin(

    @SerializedName("username") val username : String?,
    @SerializedName("password") val password : String?,
    @SerializedName("nama_pegawai") val nama_pegawai : String?,
    @SerializedName("jk") val jk : String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("is_aktif") val is_aktif : String?
)