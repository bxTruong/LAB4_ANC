package com.example.slide4_anc2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestGPS(View view) {

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    999);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latiude = location.getLatitude();
        double longtitude = location.getLongitude();

        TextView tvLocaiton = findViewById(R.id.textView);
        tvLocaiton.setText(latiude + " " + longtitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e("ABC",provider);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("ABC",provider);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("ABC",provider);

    }

    public void checkInternet(View view) {

        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        boolean isWifi=networkInfo.isConnected();

        if (isWifi) Toast.makeText(this, "WIFI is connected", Toast.LENGTH_SHORT).show();

         networkInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        boolean isMobile=networkInfo.isConnected();

        if (isMobile) Toast.makeText(this,"MOBILE is connected",Toast.LENGTH_SHORT).show();

    }
}
