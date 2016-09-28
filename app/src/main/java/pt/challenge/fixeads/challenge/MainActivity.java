package pt.challenge.fixeads.challenge;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private ArrayList<Ads> mListAds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Map"));
        tabLayout.addTab(tabLayout.newTab().setText("List"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), mListAds);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getDataServer(adapter);

    }

    private void getDataServer(final PagerAdapter adapter) {
        final ProgressDialog Dialog = new ProgressDialog(this);
        Dialog.setMessage("Downloading source...");
        Dialog.show();
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.olx.pt/i2/ads/?json=1&amp;search%5Bcategory_id%5D=25";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("Challenge", response.toString());
                mListAds.clear();
                try {
                    JSONArray testeJSON = response.getJSONArray("ads");
                    for(int i=0; i<testeJSON.length(); i++){
                        JSONObject mAds = testeJSON.getJSONObject(i);
                        Ads ads = new Ads();
                        ads.setId(mAds.getString("id"));
                        ads.setTitle(mAds.getString("title"));
                        ads.setDescription(mAds.getString("description"));
                        ads.setMap_lat(mAds.getDouble("map_lat"));
                        ads.setMap_lng(mAds.getDouble("map_lon"));
                        ads.setCity_label(mAds.getString("city_label"));
                        ads.setCreated(mAds.getString("created"));
                        ads.setIs_price_negotiable(mAds.getInt("is_price_negotiable"));
                        ads.setPrice(mAds.getString("list_label"));
                        ads.setPerson(mAds.getString("person"));
                        mListAds.add(ads);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealm(mListAds);
                realm.commitTransaction();
                adapter.notifyDataSetChanged();
                Dialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Challenge", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                Dialog.dismiss();

            }
        });

        // Adding request to request queue
        queue.add(jsonObjReq);
    }

}
