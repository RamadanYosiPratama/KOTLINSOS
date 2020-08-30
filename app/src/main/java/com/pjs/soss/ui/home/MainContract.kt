package com.pjs.soss.ui.home

interface MainContract {

    interface View {
        fun initListener()
        fun showMessage(message: String)
    }
}