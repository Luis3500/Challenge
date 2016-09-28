package pt.challenge.fixeads.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Project: Challenge
 * Created by luislopes1 on 27/09/16.
 */
public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_fragment, container, false);

        final Realm realm = Realm.getDefaultInstance();
        RealmResults<Ads> mAds = realm.where(Ads.class).findAll();
        final MyListAdapter adapter = new MyListAdapter(getContext(), mAds);

        ListView mList = (ListView)root.findViewById(R.id.listview);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("ads",adapter.getItem(i));
                startActivity(intent);
            }
        });
        mList.setAdapter(adapter);
        return root;
    }
}
