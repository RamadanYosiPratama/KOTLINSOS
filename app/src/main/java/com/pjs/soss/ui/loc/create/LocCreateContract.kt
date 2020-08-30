package com.pjs.soss.ui.loc.create

import java.io.File
import com.pjs.soss.data.model.loc.ResponseLocUpdate

interface LocCreateContract {

    interface Presenter {
        fun insertAgent(nama_toko: String, nama_pemilik: String, alamat: String, latitude: String,
                        longitude: String, gambar_toko: File
        )
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLocUpdate: ResponseLocUpdate)
        fun showMessage(message: String)
    }
}