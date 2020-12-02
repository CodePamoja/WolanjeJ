package com.wolanjeAfrica.wolanjej.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class LearningHubFeatured implements Parcelable {
    private String featured_item_image;
    private String featured_item_title;
    private String featured_item_description;
    private Date date;

    public LearningHubFeatured(String featured_item_image, String featured_item_title, String featured_item_description) {
        this.featured_item_image = featured_item_image;
        this.featured_item_title = featured_item_title;
        this.featured_item_description = featured_item_description;
    }

    protected LearningHubFeatured(Parcel in) {
        featured_item_image = in.readString();
        featured_item_title = in.readString();
        featured_item_description = in.readString();
    }

    public static final Creator<LearningHubFeatured> CREATOR = new Creator<LearningHubFeatured>() {
        @Override
        public LearningHubFeatured createFromParcel(Parcel in) {
            return new LearningHubFeatured(in);
        }

        @Override
        public LearningHubFeatured[] newArray(int size) {
            return new LearningHubFeatured[size];
        }
    };

    public String getFeatured_item_image() {
        return featured_item_image;
    }

    public void setFeatured_item_image(String featured_item_image) {
        this.featured_item_image = featured_item_image;
    }

    public String getFeatured_item_title() {
        return featured_item_title;
    }

    public void setFeatured_item_title(String featured_item_title) {
        this.featured_item_title = featured_item_title;
    }

    public String getFeatured_item_description() {
        return featured_item_description;
    }

    public void setFeatured_item_description(String featured_item_description) {
        this.featured_item_description = featured_item_description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(featured_item_image);
        dest.writeString(featured_item_title);
        dest.writeString(featured_item_description);
    }
}
