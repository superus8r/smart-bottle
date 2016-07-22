package com.mpandg.mpandgbluetooth.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ali Kabiri on 7/22/2016.
 * Find me here: ali@kabiri.org
 */
public class UserInfo implements Parcelable {

    private String username;
    private String token;
    private String sex;
    private int height;
    private int weight;
    private String type;
    private int hours;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.token);
        dest.writeString(this.sex);
        dest.writeInt(this.height);
        dest.writeInt(this.weight);
        dest.writeString(this.type);
        dest.writeInt(this.hours);
    }

    public UserInfo() {
    }

    protected UserInfo(Parcel in) {
        this.username = in.readString();
        this.token = in.readString();
        this.sex = in.readString();
        this.height = in.readInt();
        this.weight = in.readInt();
        this.type = in.readString();
        this.hours = in.readInt();
    }

    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
