package com.pjs.soss.ui.home

class MainPresenter (val  view : MainContract.View) {

    init {
        view.initListener()
    }
}