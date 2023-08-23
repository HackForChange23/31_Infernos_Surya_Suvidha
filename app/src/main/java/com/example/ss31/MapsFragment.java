package com.example.ss31;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class MapsFragment extends Fragment {
    //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark_normal_background);


    String lat, lon;
    int lati, loni;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {


            if (getArguments() != null) {

                lat = getArguments().getString("latitude");
                lon = getArguments().getString("longitude");
                double d = Double.parseDouble(lat);
                lati = (int) d;


                double dd = Double.parseDouble(lon);
                loni = (int) dd;
                //Toast.makeText(getContext(), "s" + lati + loni, Toast.LENGTH_SHORT).show();
            } else {

                //Toast.makeText(getContext(), "ts", Toast.LENGTH_SHORT).show();
                lat = "28";
                lon = "77";
            }

            LatLng sydney = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));

            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.marker);

            String snippet = String.format(Locale.getDefault(),
                    "Lat: %1$.5f, Long: %2$.5f",
                    sydney.latitude,
                    sydney.longitude);


            //googleMap.addMarker(new MarkerOptions().position(sydney).title("Your marked location"));
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Your Marked Location").snippet(snippet).icon(icon));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(8f));
            setMapLongClick(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


    private void setMapLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //map.addMarker(new MarkerOptions().position(latLng));

                BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.mapsresized);

                String snippet = String.format(Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latLng.latitude,
                        latLng.longitude);

                String lat = String.valueOf(latLng.latitude);
                String lon = String.valueOf(latLng.longitude);

                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Your Location ")
                        .snippet(snippet).icon(icon));

                Bundle bundle = new Bundle();
                bundle.putString("latitude", lat);
                bundle.putString("longitude", lon);

                FragmentManager m = getActivity().getSupportFragmentManager();
                FragmentTransaction t = m.beginTransaction();
                Fragment frame = new OpenFrame3();
                frame.setArguments(bundle);
                t.replace(R.id.openframe, frame);
                t.commit();

                /*Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra("bundle", bundle);
                startActivity(i);*/


            }
        });
    }

}