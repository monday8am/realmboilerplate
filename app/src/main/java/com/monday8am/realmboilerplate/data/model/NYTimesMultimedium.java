package com.monday8am.realmboilerplate.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NYTimesMultimedium extends RealmObject {

    @PrimaryKey
    @JsonProperty("url")
    private String mUrl;

    @JsonProperty("format")
    private String mFormat;

    @JsonProperty("height")
    private int mHeight;

    @JsonProperty("width")
    private int mWidth;

    @JsonProperty("type")
    private String mType;

    @JsonProperty("subtype")
    private String mSubtype;

    @JsonProperty("caption")
    private String mCaption;

    @JsonProperty("copyright")
    private String mCopyright;

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getSubtype() {
        return mSubtype;
    }

    public void setSubtype(String subtype) {
        mSubtype = subtype;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }
}
