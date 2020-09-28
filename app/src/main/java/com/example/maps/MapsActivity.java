package com.example.maps;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spark.submitbutton.SubmitButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements  OnMapReadyCallback, GoogleMap.OnMarkerDragListener, LocationListener {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private LocationManager locationManager;
    SubmitButton Confirmation;
    MaterialRippleLayout Account;
   TextView TotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        TotalPrice = findViewById(R.id.TotalPrice);
        TotalPrice.setText(String.valueOf(User.TotalPrice));

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        Confirmation = findViewById(R.id.confirm);
        Confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
            }
        });
        Account = findViewById(R.id.account);
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerDragListener(this);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.0443156,31.2386724), 8));

        mMap.clear();
        Location location = null;
        try {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            Log.d(TAG, "onClick: You did not allow to access the current location");
        }
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("My Location")).setDraggable(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            //setAddressOnMap(latLng.latitude, latLng.longitude);
        } else {
            Log.d(TAG, "onClick: Please wait until your position is determined");
        }
    }

    /*private void setAddressOnMap(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (!addresses.isEmpty()) {
                String addressStr = addresses.get(0).getThoroughfare();
               Address.setText(addressStr);
            } else {
                Log.d(TAG, "setAddressOnMap: No address for this location");
              Address.getText().clear();
            }
        } catch (IOException e) {
            Log.d(TAG, "setAddressOnMap: Can't get the address, Check your network");
        }
    }*/


    @Override
    public void onMarkerDragStart(Marker marker) {
        Log.d(TAG, "onMarkerDragStart: ");
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.d(TAG, "onMarkerDrag: ");
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //setAddressOnMap(marker.getPosition().latitude, marker.getPosition().longitude);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d(TAG, "onLocationChanged: " + location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.d(TAG, "onProviderEnabled: ");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.d(TAG, "onProviderDisabled: ");
    }


}