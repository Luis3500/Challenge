package pt.challenge.fixeads.challenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Ads mAds = (Ads) intent.getExtras().get("ads");
        ((TextView)findViewById(R.id.title)).setText(mAds.getTitle());
        ((TextView)findViewById(R.id.description)).setText(mAds.getDescription());
        ((TextView)findViewById(R.id.price)).setText(mAds.getPrice());
        ((TextView)findViewById(R.id.person)).setText(mAds.getPerson());
        ((TextView)findViewById(R.id.city)).setText(mAds.getCity_label());
        ((TextView)findViewById(R.id.id_tag)).setText(mAds.getId());
        ((TextView)findViewById(R.id.created)).setText(mAds.getCreated());
        findViewById(R.id.nego).setVisibility(mAds.getIs_price_negotiable() == 1 ? View.VISIBLE : View.GONE);

    }
}
