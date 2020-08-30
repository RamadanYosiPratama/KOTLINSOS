package com.pjs.soss.ui.loc

import com.pjs.soss.data.model.loc.ResponseLoclist

interface LocContract {


    interface Presenter {
        fun getLoc()
    }
    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingLoc(loading: Boolean)
        fun onResultLoc(responseLocList: ResponseLoclist)
        fun showMessage(message: String)
    }
}