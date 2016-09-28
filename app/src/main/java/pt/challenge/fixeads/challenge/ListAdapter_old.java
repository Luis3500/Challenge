package pt.challenge.fixeads.challenge;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Project: Challenge
 * Created by luislopes1 on 28/09/16.
 */
public class ListAdapter_old extends ArrayAdapter<Ads> {

    private ArrayList<Ads> mListAds;
    private int mLayoutResourceId;
    private Context mContext;

    public ListAdapter_old(Context context, int resource, ArrayList<Ads> mListAds) {
        super(context, resource, mListAds);
        this.mListAds = mListAds;
        this.mLayoutResourceId = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AdsHolder mHolder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);

            mHolder = new AdsHolder();
            mHolder.txtTitle = (TextView) row.findViewById(R.id.txtHeader);
            row.setTag(mHolder);

        } else {
            mHolder= (AdsHolder) row.getTag();
        }
        Ads mAds = mListAds.get(position);
        mHolder.txtTitle.setText(mAds.getTitle());
        return row;
    }

    static class AdsHolder
    {
        TextView txtTitle;
    }
}
