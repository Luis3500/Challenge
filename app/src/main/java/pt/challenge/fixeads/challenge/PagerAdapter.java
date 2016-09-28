package pt.challenge.fixeads.challenge;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Project: Challenge
 * Created by luislopes1 on 27/09/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    ArrayList<Ads> mListAds;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Ads> mListAds) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mListAds = mListAds;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MapFragment mMapTab = new MapFragment();
                mMapTab.updateMarkers(mListAds);
                return mMapTab;
            case 1:
                ListFragment mListTab = new ListFragment();
                return mListTab;
            default:
                return null;
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
