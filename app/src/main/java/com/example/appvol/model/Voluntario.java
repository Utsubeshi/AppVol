package com.example.appvol.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Voluntario implements Parcelable {
    protected Voluntario(Parcel in) {
    }

    public static final Creator<Voluntario> CREATOR = new Creator<Voluntario>() {
        @Override
        public Voluntario createFromParcel(Parcel in) {
            return new Voluntario(in);
        }

        @Override
        public Voluntario[] newArray(int size) {
            return new Voluntario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
