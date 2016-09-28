package pt.challenge.fixeads.challenge;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Project: Challenge
 * Created by luislopes1 on 27/09/16.
 */
public class Ads extends RealmObject implements Parcelable {

    private String id;
    private String url;
    private String title;
    private String description;
    private Double map_lat;
    private Double map_lng;
    private String price;
    private int is_price_negotiable;
    private String person;
    private String city_label;
    private String created;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMap_lat() {
        return map_lat;
    }

    public void setMap_lat(Double map_lat) {
        this.map_lat = map_lat;
    }

    public Double getMap_lng() {
        return map_lng;
    }

    public void setMap_lng(Double map_lng) {
        this.map_lng = map_lng;
    }

    public String getPrice() { return price;}

    public void setPrice(String price) { this.price = price;}

    public int getIs_price_negotiable() { return is_price_negotiable; }

    public void setIs_price_negotiable(int is_price_negotiable) {this.is_price_negotiable = this.is_price_negotiable; }

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person; }

    public String getCity_label() { return city_label; }

    public void setCity_label(String city_label) { this.city_label = city_label; }

    public String getCreated() { return created; }

    public void setCreated(String created) {this.created = created; }

    public Ads() {
    }

    protected Ads(Parcel in) {
        id = in.readString();
        url = in.readString();
        title = in.readString();
        description = in.readString();
        map_lat = in.readByte() == 0x00 ? null : in.readDouble();
        map_lng = in.readByte() == 0x00 ? null : in.readDouble();
        price = in.readString();
        is_price_negotiable = in.readInt();
        person = in.readString();
        city_label = in.readString();
        created = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
        dest.writeString(title);
        dest.writeString(description);
        if (map_lat == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(map_lat);
        }
        if (map_lng == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(map_lng);
        }
        dest.writeString(price);
        dest.writeInt(is_price_negotiable);
        dest.writeString(person);
        dest.writeString(city_label);
        dest.writeString(created);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ads> CREATOR = new Parcelable.Creator<Ads>() {
        @Override
        public Ads createFromParcel(Parcel in) {
            return new Ads(in);
        }

        @Override
        public Ads[] newArray(int size) {
            return new Ads[size];
        }
    };
}
