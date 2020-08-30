package com.pjs.soss.data.model.loc

import com.google.gson.annotations.SerializedName

data class ResponseLocUpdate(
    @SerializedName("status") val status: Boolean,
    @SerializedName("msg") val msg: String
)