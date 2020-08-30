package com.pjs.soss.data.model.login

import com.google.gson.annotations.SerializedName
import com.pjs.soss.data.model.login.DataLogin


data class ResponseLogin(

    @SerializedName("username") val status: Boolean,
    @SerializedName("msg") val msg: String,
    @SerializedName("pegawai") val pegawai: DataLogin?
)