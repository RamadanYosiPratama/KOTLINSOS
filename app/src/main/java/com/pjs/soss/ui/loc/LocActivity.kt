package com.pjs.soss.ui.loc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pjs.soss.R
import com.pjs.soss.ui.loc.LocAdapter
import com.pjs.soss.data.model.loc.DataLoc
import com.pjs.soss.data.model.loc.ResponseLoclist
import com.pjs.soss.ui.loc.create.LocCreateActivity
import com.pjs.soss.utils.MapsHelper
import kotlinx.android.synthetic.main.activity_loc.*
import kotlinx.android.synthetic.main.content_loc.*

class LocActivity : AppCompatActivity(), LocContract.View {

    lateinit var presenter: LocPresenter
    lateinit var locAdapter: LocAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc)
        setSupportActionBar(toolbar)
        presenter = LocPresenter(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getLoc()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Location"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        MapsHelper.permissionMap(this, this)
    }

    override fun initListener() {

        locAdapter = LocAdapter(this, arrayListOf())

        rcvLoc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = locAdapter
        }
        swipe.setOnRefreshListener {
            presenter.view
        }
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            startActivity( Intent(this, LocCreateActivity::class.java) )
        }
    }

    override fun onLoadingLoc(loading: Boolean) {
        when(loading) {
            true -> swipe.isRefreshing = true
            false -> swipe.isRefreshing = false
        }
    }


    override fun onResultLoc(responseLocList: ResponseLoclist) {
        val dataLoc: List<DataLoc> = responseLocList.dataLoc
        locAdapter.setData( dataLoc )
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
