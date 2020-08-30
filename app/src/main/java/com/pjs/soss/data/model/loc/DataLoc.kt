package com.pjs.soss.data.model.loc

import com.google.gson.annotations.SerializedName

data class DataLoc(

        @SerializedName("kd_agent") val kd_agen: Long?,
        @SerializedName("nama_toko") val nama_toko: String?,
        @SerializedName("nama_pemilik") val nama_pemilik: String?,
        @SerializedName("alamat") val alamat: String?,
        @SerializedName("latitude") val latitude: String?,
        @SerializedName("longtitude") val longtitude: String?,
        @SerializedName("gambar_toko") val gambar_toko: String?
)