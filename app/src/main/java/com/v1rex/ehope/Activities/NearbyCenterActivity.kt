package com.v1rex.ehope.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.v1rex.ehope.R

class NearbyCenterActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_center)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val newYork = LatLng(40.6971494, -74.2598732)
        var default1 = LatLng(40.6971494,-74.2598732)
        var default2 = LatLng(40.6794469, -74.2900856)
        var default3 = LatLng(41.4013876, -74.5372779)
        var default4 = LatLng(41.6494236, -75.1374061)
        var default5 = LatLng(41.6410855, -75.1772315)
        var default6 = LatLng(29.7134965, -95.0862073)
        var default7 = LatLng(29.7827251, -95.3521107)



        mMap.addMarker(MarkerOptions().position(default1).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default2).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default3).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default4).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default5).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default6).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))
        mMap.addMarker(MarkerOptions().position(default7).title("Placeholder").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)))

        mMap.addMarker(MarkerOptions().position(newYork).title("Your position"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newYork))
    }
}
