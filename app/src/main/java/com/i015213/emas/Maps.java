package com.i015213.emas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class Maps extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady = false;

    static final CameraPosition PASTO = CameraPosition.builder()
            .target(new LatLng(1.2089284,-77.279443))
            .zoom(15)
            .bearing(90)
            .tilt(45)
            .build();

    static final CameraPosition DUBLIN = CameraPosition.builder()
            .target(new LatLng(53.3478,-6.2597))
            .zoom(15)
            .bearing(90)
            .tilt(45)
            .build();

    static final CameraPosition TOKYIO = CameraPosition.builder()
            .target(new LatLng(35.6895,139.6917))
            .zoom(15)
            .bearing(90)
            .tilt(45)
            .build();

    static final CameraPosition SEATTLE = CameraPosition.builder()
            .target(new LatLng(47.6204,-122.3491))
            .zoom(15)
            .bearing(90)
            .tilt(45)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button btnNormal = (Button) findViewById(R.id.id_btn_pasto);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityChange(SEATTLE);
                //m_map.animateCamera(CameraUpdateFactory.newCameraPosition(SEATTLE), 10000, null);
            }
        });

        Button btnSatelite = (Button) findViewById(R.id.id_btn_dublin);
        btnSatelite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CityChange(DUBLIN);
            }
        });

        Button btnHibrido = (Button) findViewById(R.id.id_btn_tokyo);
        btnHibrido.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CityChange(TOKYIO);
            }
        });

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void CityChange(CameraPosition cameraposition) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraposition), 2000, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;

        //LatLng pasto = new LatLng(1.2089284, -77.2779443);
        //CameraPosition target = CameraPosition.builder().target(pasto).zoom(15).build();
        // Carga la configuración para ser puesta en el mapa
        //m_map.moveCamera(CameraUpdateFactory.newCameraPosition(PASTO));

        //Animación de camara
        //m_map.animateCamera(CameraUpdateFactory.newCameraPosition(PASTO), 10000, null);
        CityChange(PASTO);


    }
}
