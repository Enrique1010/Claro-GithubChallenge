package me.jansv.challenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repos implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    protected Repos(Parcel in){
        name = in.readString();
        htmlUrl = in.readString();
    }

    public static final Creator<Repos> CREATOR = new Creator<Repos>() {
        @Override
        public Repos createFromParcel(Parcel in) {
            return new Repos(in);
        }

        @Override
        public Repos[] newArray(int size) {
            return new Repos[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(htmlUrl);

    }
}
