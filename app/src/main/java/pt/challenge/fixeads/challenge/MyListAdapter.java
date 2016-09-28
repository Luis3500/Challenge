package pt.challenge.fixeads.challenge;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Project: Challenge
 * Created by luislopes1 on 28/09/16.
 */
public class MyListAdapter extends RealmBaseAdapter<Ads> implements ListAdapter {

    private Context mContext;

    public MyListAdapter(@NonNull Context context, @Nullable OrderedRealmCollection<Ads> data) {
        super(context, data);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AdsHolder mHolder;

        if (row == null) {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.listview_header_row, parent, false);

            mHolder = new AdsHolder();
            mHolder.txtTitle = (TextView) row.findViewById(R.id.txtHeader);
            row.setTag(mHolder);

        } else {
            mHolder= (AdsHolder) row.getTag();
        }
        Ads mAds = adapterData.get(position);
        mHolder.txtTitle.setText(mAds.getTitle());
        return row;
    }

    static class AdsHolder
    {
        TextView txtTitle;
    }
}
