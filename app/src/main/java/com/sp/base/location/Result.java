package com.sp.base.location;

import com.google.android.gms.maps.model.LatLng;

public class Result {
        public LatLng latLng;
        public String snapshotPath;
        public Result (LatLng latLng, String snapshotPath) {
            this.latLng = latLng;
            this.snapshotPath = snapshotPath;
        }
    }