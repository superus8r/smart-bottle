package com.mpandg.mpandgbluetooth.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ali Kabiri on 7/17/2016.
 * Find me here: ali@kabiri.org
 */
public class BodyType implements Parcelable {
    public static final String KEY = "body_type";

    private String name;
    private int color;
    private int photo;
    private String description;

    public BodyType (String name, int color, int photo) {

        this.name = name;
        this.color = color;
        this.photo = photo;
    }

    public static String getKEY() {
        return KEY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeInt(this.color);
        dest.writeInt(this.photo);
        dest.writeString(this.name);
    }

    public BodyType() {
    }

    protected BodyType(Parcel in) {
        this.description = in.readString();
        this.color = in.readInt();
        this.photo = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<BodyType> CREATOR = new Parcelable.Creator<BodyType>() {
        @Override
        public BodyType createFromParcel(Parcel source) {
            return new BodyType(source);
        }

        @Override
        public BodyType[] newArray(int size) {
            return new BodyType[size];
        }
    };
}
