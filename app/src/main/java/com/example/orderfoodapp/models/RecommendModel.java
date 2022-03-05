package com.example.orderfoodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RecommendModel implements Parcelable {

    @SerializedName("_id")
    String _id;

    @SerializedName("image")
    String image;

    @SerializedName("name")
    String name;

    @SerializedName("timing")
    String timing;

    @SerializedName("rating")
    String rating;

    @SerializedName("owner_category")
    String owner_category;

    @SerializedName("description")
    String description;

    @SerializedName("price")
    String price;


    public RecommendModel() {
    }

    public RecommendModel(String _id, String image, String name, String timing, String rating, String owner_category, String description, String price) {
        this._id = _id;
        this.image = image;
        this.name = name;
        this.timing = timing;
        this.rating = rating;
        this.owner_category = owner_category;
        this.description = description;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public String getOwner_category() {
        return owner_category;
    }

    public void setOwner_category(String owner_category) {
        this.owner_category = owner_category;
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

    public static Creator<RecommendModel> getCREATOR() {
        return CREATOR;
    }

    protected RecommendModel(Parcel in) {
        _id = in.readString();
        image = in.readString();
        name = in.readString();
        timing = in.readString();
        rating = in.readString();
        owner_category = in.readString();
        description = in.readString();
        price = in.readString();
    }

    public static final Creator<RecommendModel> CREATOR = new Creator<RecommendModel>() {
        @Override
        public RecommendModel createFromParcel(Parcel in) {
            return new RecommendModel(in);
        }

        @Override
        public RecommendModel[] newArray(int size) {
            return new RecommendModel[size];
        }
    };

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
        parcel.writeString(owner_category);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(_id);
    }
}
