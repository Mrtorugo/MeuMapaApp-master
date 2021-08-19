package br.com.etecia.meumapaapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.etecia.meumapaapp.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //variável global
    private GoogleMap mMap;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Variavel para configuração das estruturas do mapa.
        mMap = googleMap;

        //Tipo de mapas
        /*
        Normal
            Mapa rodoviário comum. Mostra vias, alguns elementos criados pelo homem e
            recursos naturais importantes, como rios. Etiquetas de estradas e de elementos
             também são visíveis.
        Híbrido
            Dados de fotografia de satélite com mapas rodoviários. Etiquetas de estradas
             e de elementos também são visíveis.
        Satélite
            Dados de fotografia de satélite. Marcadores de estradas e de elementos não são visíveis.
        Relevo
            Dados topográficos. O mapa inclui cores, curvas de nível e etiquetas, além de sombreamento
             de perspectiva. Algumas vias e etiquetas também são visíveis.
        Nenhum
            Nenhum bloco. O mapa será renderizado como uma grade vazia, sem carregar blocos.
        */

        //método para alterar o tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        latitude = -23.5856101;
        longitude = -46.6669873;

        String local = "Parque do Ibirapuera";

        LatLng posicao = new LatLng(latitude, longitude);

        mMap.addMarker(new MarkerOptions()
                .position(posicao)
                .snippet("Local do parque do ibirapuera")
                //.icon(BitmapDescriptorFactory.defaultMarker(
                //        BitmapDescriptorFactory.HUE_VIOLET
               // ))
                //Icone costumizado
                 .icon(BitmapDescriptorFactory.fromResource(R.drawable.escola))
                .title(local));

        //Movimento da camera conforme a necessidade.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicao,15));

        //Adicionando evento de click no mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                double latitude, longitude;

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Click curto - Lat: " + latitude + " Long: " + longitude,
                        Toast.LENGTH_SHORT).show();
                //Gerando marcador de click curto
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Clique curto")
                        .snippet("Descrição do click curto")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus))
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
            }
        });

        //Gerando click longo

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                //Salvando a latitude e longitude
                double latitute, longitude;

                latitute = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Click Longo - Lat: " + latitute + "Lon: " + longitude,
                        Toast.LENGTH_SHORT).show();
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Click longo")
                        .snippet("Descrição do click longo")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
            }
        });


    }
}