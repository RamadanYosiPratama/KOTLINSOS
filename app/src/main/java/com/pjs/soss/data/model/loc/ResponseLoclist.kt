package com.pjs.soss.data.model.loc

import com.google.gson.annotations.SerializedName
import com.pjs.soss.data.model.loc.DataLoc

data class ResponseLoclist(

    @SerializedName("status")  val status: Boolean,
    @SerializedName("data") val dataLoc: List<DataLoc>

)