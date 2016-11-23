package com.monday8am.realmboilerplate.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.monday8am.realmboilerplate.data.remote.RealmListNYTimesMultimediumDeserializer;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class NYTimesStory extends RealmObject {

    public static final String PUBLISHED_DATE = "publishedDate";
    public static final String URL = "url";
    public static final String API_SECTION = "apiSection";

    private String mApiSection;

    // have incorrect indentation level 8, expected level should be 12.
    @JsonProperty("section")
    private String mSection;

    @JsonProperty("subsection")
    private String mSubsection;

    @JsonProperty("title")
    private String mTitle;

    @JsonProperty("abstract")
    private String mStoryAbstract;

    @PrimaryKey
    @JsonProperty("url")
    private String mUrl;

    @JsonProperty("byline")
    private String mByline;

    @JsonProperty("item_type")
    private String mItemType;

    @JsonProperty("updated_date")
    private String mUpdatedDate;

    @JsonProperty("created_date")
    private String mCreatedDate;

    @JsonProperty("published_date")
    private String mPublishedDate;

    @JsonProperty("material_type_facet")
    private String mMaterialTypeFacet;

    @JsonProperty("kicker")
    private String mKicker;

    @JsonProperty("multimedia")
    private RealmList<NYTimesMultimedium> mMultimedia;

    private long mSortTimeStamp;

    private boolean mRead;

    public String getSection() {
        return mSection;
    }

    public void setSection(String mSection) {
        this.mSection = mSection;
    }

    public String getSubsection() {
        return mSubsection;
    }

    public void setSubsection(String mSubsection) {
        this.mSubsection = mSubsection;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getStoryAbstract() {
        return mStoryAbstract;
    }

    public void setStoryAbstract(String storyAbstract) {
        mStoryAbstract = storyAbstract;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getByline() {
        return mByline;
    }

    public void setByline(String byline) {
        mByline = byline;
    }

    public String getItemType() {
        return mItemType;
    }

    public void setItemType(String itemType) {
        mItemType = itemType;
    }

    public String getUpdatedDate() {
        return mUpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        mUpdatedDate = updatedDate;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        this.mCreatedDate = createdDate;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getMaterialTypeFacet() {
        return mMaterialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        mMaterialTypeFacet = materialTypeFacet;
    }

    public String getKicker() {
        return mKicker;
    }

    public void setKicker(String kicker) {
        mKicker = kicker;
    }

    public RealmList<NYTimesMultimedium> getMultimedia() {
        return mMultimedia;
    }

    @JsonDeserialize(using = RealmListNYTimesMultimediumDeserializer.class)
    public void setMultimedia(RealmList<NYTimesMultimedium> multimedia) {
        mMultimedia = multimedia;
    }

    public long getSortTimeStamp() {
        return mSortTimeStamp;
    }

    public void setSortTimeStamp(long sortTimeStamp) {
        mSortTimeStamp = sortTimeStamp;
    }

    public boolean isRead() {
        return mRead;
    }

    public void setRead(boolean read) {
        mRead = read;
    }

    public String getApiSection() {
        return mApiSection;
    }

    public void setApiSection(String apiSection) {
        mApiSection = apiSection;
    }

}
