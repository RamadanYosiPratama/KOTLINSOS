package com.pjs.soss.ui.loc.create

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import com.lazday.poslaravel.util.GalleryHelper
import com.pjs.soss.R
import com.pjs.soss.data.Constant
import com.pjs.soss.data.model.loc.ResponseLocUpdate
import com.pjs.soss.ui.loc.LocMapsActivity
import com.pjs.soss.utils.FileUtils
import kotlinx.android.synthetic.main.activity_loc_create.*

class LocCreateActivity : AppCompatActivity(), LocCreateContract.View {

    private var uriImage: Uri? = null
    private var pickImage = 1
    lateinit var presenter: LocCreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc_create)
        presenter = LocCreatePresenter(this)

    }

    override fun onStart() {
        super.onStart()
        if (Constant.LATITUDE.isNotEmpty()) {
            edtLocation.setText("${Constant.LATITUDE}, ${Constant.LONGITUDE}")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Constant.LATITUDE = ""
        Constant.LONGITUDE = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == Activity.RESULT_OK) {
            uriImage = data!!.data
            imvImage.setImageURI( uriImage )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun initActivity() {
        supportActionBar!!.title = "Lokasi Kejadian"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun initListener() {
        edtLocation.setOnClickListener {
            startActivity(Intent(this, LocMapsActivity::class.java))
        }
        imvImage.setOnClickListener {
            if (GalleryHelper.permissionGallery(this, this, pickImage)){
                GalleryHelper.openGallery(this)
            }
        }
        btnSubmit.setOnClickListener {
            val namaStore = edtNameKejadian.text
            val namaOwner = edtPerusahaan.text
            val address = edtAddress.text
            val location = edtLocation.text

            if (namaStore.isNullOrEmpty() || namaOwner.isNullOrEmpty() || address.isNullOrEmpty() ||
                    location.isNullOrEmpty() || uriImage == null) {
                showMessage("Lengkapi data dengan benar")
            } else {
                presenter.insertAgent( namaStore.toString(), namaOwner.toString(), address.toString(),
                Constant.LATITUDE, Constant.LONGITUDE, FileUtils.getFile(this, uriImage))
            }
        }
    }

    override fun onLoading(loading: Boolean) {
        when (loading) {
            true -> {
                progress.visibility = View.VISIBLE
                btnSubmit.visibility = View.GONE
            }
            false ->   {
                progress.visibility = View.GONE
                btnSubmit.visibility = View.VISIBLE
            }
        }
    }

    override fun onResult(responseLocUpdate: ResponseLocUpdate) {
        showMessage(responseLocUpdate.msg)
        finish()
    }


    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
