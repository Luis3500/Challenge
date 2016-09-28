package pt.challenge.fixeads.challenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Project: Challenge
 * Created by luislopes1 on 27/09/16.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Ads> mListAds;
    private Realm realm;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);
        MapView mMapView = (MapView) rootView.findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm = Realm.getDefaultInstance();
        mMapView.getMapAsync(this);
        return rootView;
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
        setMarkers();
    }

    public void updateMarkers(ArrayList<Ads> mListAds) {
        this.mListAds = mListAds;
        setMarkers();
    }

    private void setMarkers() {
        if ( mMap == null) return;

        RealmResults<Ads> mListAds = realm.where(Ads.class).findAllAsync();
        mListAds.addChangeListener(new RealmChangeListener<RealmResults<Ads>>() {
            @Override
            public void onChange(RealmResults<Ads> element) {
                mMap.clear();
                LatLngBounds mapBounds = null;
                for (int i = 0; i < element.size(); i++) {
                    Marker mMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(element.get(i).getMap_lat(),
                            element.get(i).getMap_lng())).title(element.get(i).getTitle()));
                    if (mapBounds == null)
                        mapBounds = new LatLngBounds(mMarker.getPosition(), mMarker.getPosition());
                    else
                        mapBounds = mapBounds.including(mMarker.getPosition());
                }
                if (mapBounds == null) return;
                CameraUpdate mapCamera = CameraUpdateFactory.newLatLngBounds(mapBounds, 200);
                mMap.moveCamera(mapCamera);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
