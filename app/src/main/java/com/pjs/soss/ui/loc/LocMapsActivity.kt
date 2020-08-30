package com.pjs.soss.ui.loc

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pjs.soss.R
import com.pjs.soss.data.Constant

class LocMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val marker = MarkerOptions()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc_maps)
        supportActionBar!!.title = "Maps"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f) )

                Constant.LATITUDE = location.latitude.toString()
                Constant.LONGITUDE = location.longitude.toString()

                marker.position(currentLatLng)
                googleMap.addMarker(marker)

            }

        }
        googleMap.setOnMapClickListener { latLng ->
            marker.position ( latLng )
            marker.title ( latLng.latitude.toString() + " : " + latLng.longitude.toString())

            Constant.LATITUDE = latLng.latitude.toString()
            Constant.LONGITUDE = latLng.longitude.toString()
            Log.d("LocMapsActivity", "Lat $(Constant.LATITUDE) LNG $(Constant.LONGITUDE)")

            googleMap.clear()
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            googleMap.addMarker(marker)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_maps, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        finish()
        return
        super.onSupportNavigateUp()
    }
}
