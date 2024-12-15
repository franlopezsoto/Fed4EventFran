package com.example.feedback4eventosfranlopez;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

public class NovelasMapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private NovelaViewModel novelaViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novelas_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        novelaViewModel = new ViewModelProvider(requireActivity()).get(NovelaViewModel.class);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        novelaViewModel.getAllNovelas().observe(getViewLifecycleOwner(), new Observer<List<Novela>>() {
            @Override
            public void onChanged(List<Novela> novelas) {
                for (Novela novela : novelas) {
                    LatLng location = new LatLng(novela.getLatitude(), novela.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(location).title(novela.getTitle()));
                }
                if (!novelas.isEmpty()) {
                    LatLng firstLocation = new LatLng(novelas.get(0).getLatitude(), novelas.get(0).getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstLocation, 10));
                }
            }
        });
    }
}