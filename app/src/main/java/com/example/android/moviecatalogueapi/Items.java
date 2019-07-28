package com.example.android.moviecatalogueapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("poster_path")
    @Expose
    private String photo;

    @SerializedName("title")
    @Expose
    private String name;

    @SerializedName("name")
    @Expose
    private String tvName;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("first_air_date")
    @Expose
    private String TvReleaseDate;

    @SerializedName("vote_average")
    @Expose
    private float rating;

    @SerializedName("overview")
    @Expose
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTvReleaseDate() {
        return TvReleaseDate;
    }

    public void setTvReleaseDate(String tvReleaseDate) {
        TvReleaseDate = tvReleaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
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
        dest.writeInt(this.id);
        dest.writeString(this.photo);
        dest.writeString(this.name);
        dest.writeString(this.tvName);
        dest.writeString(this.releaseDate);
        dest.writeString(this.TvReleaseDate);
        dest.writeFloat(this.rating);
        dest.writeString(this.description);
    }

    public Items() {
    }

    protected Items(Parcel in) {
        this.id = in.readInt();
        this.photo = in.readString();
        this.name = in.readString();
        this.tvName = in.readString();
        this.releaseDate = in.readString();
        this.TvReleaseDate = in.readString();
        this.rating = in.readFloat();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel source) {
            return new Items(source);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };
}
