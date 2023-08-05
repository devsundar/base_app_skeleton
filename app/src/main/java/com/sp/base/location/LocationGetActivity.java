package com.sp.base.location;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.sp.base.R;

public class LocationGetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_get);
        LocationProvider locationProvider = new LocationProvider();

        locationProvider.requestEnableLocationServices(this);
        locationProvider.requestLocationUpdates(this, 1, 2);

        locationProvider.getLastLocation(this).map(location -> {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

//            int width = getResources().getDimensionPixelSize(R.dimen.message_image_max_width);
//            int height = getResources().getDimensionPixelSize(R.dimen.message_image_max_height);

//            return new Result(latLng, GoogleUtils.getMapImageURL(latLng, width, height));
            Log.e("Sp", "" + latLng.latitude + " " + latLng.longitude);
            return "";
        });
    }
}