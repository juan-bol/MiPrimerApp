package com.appmoviles.miprimerapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DialogFragMarker.DialogFragMarkerActions {

    private static final int REQUEST_CODE = 11 ;
    private GoogleMap mMap;
    private LocationManager manager;
    private Marker me;
    private List<Marker> markers;
    private FloatingActionButton fab_limpiar;

    private DialogFragMarker dialogFragMarker;
    private String nombreMarcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        dialogFragMarker = new DialogFragMarker();

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        markers = new ArrayList<Marker>();

        fab_limpiar = findViewById(R.id.fab_limpiar);
        fab_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                Toast.makeText(MapsActivity.this, "clear", Toast.LENGTH_LONG).show();
            }
        });
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                openDialogMarker();
                mMap.addMarker(new MarkerOptions().position(latLng).title(nombreMarcador));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });

        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Toast.makeText(this, "Not Enough Permission", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            //Agregar el listener de ubicacion
            if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        String msj = "LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude();
                        Log.e(">>>","LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude());
                        Toast.makeText(MapsActivity.this, msj, Toast.LENGTH_LONG).show();

                        if(me != null) me.remove();
                        LatLng myPosition = new LatLng(location.getLatitude(), location.getLongitude());
                        me = mMap.addMarker(new MarkerOptions().position(myPosition)
                                .title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 10));

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
            }
            else if(manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.e(">>>","LAT: "+location.getLatitude()+ " , LONG: "+location.getLongitude());

                        if(me != null){
                            me.remove();
                        }
                        me = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                                .title("Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
            }
        }

    }

    public void openDialogMarker(){
        DialogFragMarker dialogo = new DialogFragMarker();
        dialogo.show(getSupportFragmentManager(),"Dialogo MarcadorZ");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void obtenerNombreMarcador(String nombreMarcador) {
        nombreMarcador = nombreMarcador;
    }
}
