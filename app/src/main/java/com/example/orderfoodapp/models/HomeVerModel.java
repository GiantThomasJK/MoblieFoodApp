package com.example.orderfoodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HomeVerModel implements Parcelable {
        @SerializedName("image")
        String image;

        @SerializedName("name")
        String name;

        @SerializedName("timing")
        String timing;

        @SerializedName("rating")
        String rating;

        @SerializedName("_id")
        String _id;

        @SerializedName("description")
        String description;

        @SerializedName("price")
        String price;

        @SerializedName("owner_category")
        String owner_category;

    public HomeVerModel() {
    }

    public HomeVerModel(String image, String name, String timing, String rating, String _id, String description, String price, String owner_category) {
        this.image = image;
        this.name = name;
        this.timing = timing;
        this.rating = rating;
        this._id = _id;
        this.description = description;
        this.price = price;
        this.owner_category = owner_category;
    }

    protected HomeVerModel(Parcel in) {
        image = in.readString();
        name = in.readString();
        timing = in.readString();
        rating = in.readString();
        _id = in.readString();
        description = in.readString();
        price = in.readString();
        owner_category = in.readString();
    }

    public static final Creator<HomeVerModel> CREATOR = new Creator<HomeVerModel>() {
        @Override
        public HomeVerModel createFromParcel(Parcel in) {
            return new HomeVerModel(in);
        }

        @Override
        public HomeVerModel[] newArray(int size) {
            return new HomeVerModel[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwner_category() {
        return owner_category;
    }

    public void setOwner_category(String owner_category) {
        this.owner_category = owner_category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image);
        parcel.writeString(name);
        parcel.writeString(timing);
        parcel.writeString(rating);
        parcel.writeString(_id);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(owner_category);

    }
}
