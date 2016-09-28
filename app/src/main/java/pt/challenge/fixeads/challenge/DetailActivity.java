package pt.challenge.fixeads.challenge;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends FragmentActivity {

    private MyAdapter mAdapter;
    private ViewPager mPager;
    static private ArrayList<Ads> ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        int index = (int) intent.getExtras().get("index");
        ads = intent.getParcelableArrayListExtra("ads");
        Ads mAds = ads.get(index);
        ads.remove(index);
        ads.add(0, mAds);

        mAdapter = new MyAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

    }

    public static class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return ads.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }
    }

    public static class ArrayListFragment extends Fragment {
        int mNum;
        private Ads mAdsLocal;
        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 0;
            mAdsLocal = ads.get(mNum);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);

            ((TextView)v.findViewById(R.id.title)).setText(mAdsLocal.getTitle());
            ((TextView)v.findViewById(R.id.description)).setText(mAdsLocal.getDescription());
            ((TextView)v.findViewById(R.id.price)).setText(mAdsLocal.getPrice());
            ((TextView)v.findViewById(R.id.person)).setText(mAdsLocal.getPerson());
            ((TextView)v.findViewById(R.id.city)).setText(mAdsLocal.getCity_label());
            ((TextView)v.findViewById(R.id.id_tag)).setText(mAdsLocal.getId());
            ((TextView)v.findViewById(R.id.created)).setText(mAdsLocal.getCreated());
            v.findViewById(R.id.nego).setVisibility(mAdsLocal.getIs_price_negotiable() == 1 ? View.VISIBLE : View.GONE);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        }

    }

}
