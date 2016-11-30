package com.monday8am.realmboilerplate.data.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.monday8am.realmboilerplate.data.remote.RealmListNYTimesMultimediumDeserializer;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/*
 * NYTimesStory used as POJO and as a Realm object
 * at the same time.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "section",
        "subsection",
        "title",
        "abstract",
        "url",
        "thumbnail_standard",
        "short_url",
        "byline",
        "item_type",
        "updated_date",
        "created_date",
        "published_date",
        "material_type_facet",
        "kicker",
        "des_facet",
        "org_facet",
        "per_facet",
        "geo_facet",
        "multimedia",
        "related_urls"
        })
public class NYTimesStory extends RealmObject {
    public static final String PUBLISHED_DATE = "publishedDate";
    public static final String URL = "url";
    public static final String API_SECTION = "apiSection";

    public long sortTimeStamp;
    public boolean isRead;
    public String apiSection;

    @PrimaryKey
    @JsonProperty("url")
    public String url;

    @JsonProperty("section")
    public String section;
    @JsonProperty("subsection")
    public String subsection;
    @JsonProperty("title")
    public String title;
    @JsonProperty("abstract")
    public String _abstract;
    @JsonProperty("thumbnail_standard")
    public String thumbnailStandard;
    @JsonProperty("short_url")
    public String shortUrl;
    @JsonProperty("byline")
    public String byline;
    @JsonProperty("item_type")
    public String itemType;
    @JsonProperty("updated_date")
    public String updatedDate;
    @JsonProperty("created_date")
    public String createdDate;
    @JsonProperty("published_date")
    public String publishedDate;
    @JsonProperty("material_type_facet")
    public String materialTypeFacet;
    @JsonProperty("kicker")
    public String kicker;
    @Ignore
    @JsonProperty("des_facet")
    public List<String> desFacet = new ArrayList<String>();
    @Ignore
    @JsonProperty("org_facet")
    public List<String> orgFacet = new ArrayList<String>();
    @Ignore
    @JsonProperty("per_facet")
    public List<String> perFacet = new ArrayList<String>();
    @Ignore
    @JsonProperty("geo_facet")
    public List<String> geoFacet = new ArrayList<String>();
    @Ignore
    @JsonProperty("related_urls")
    public List<NYTimesRelatedUrl> relatedUrls = new ArrayList<NYTimesRelatedUrl>();
    @Ignore
    @JsonIgnore
    private Map<String, Object> mAdditionalProperties = new HashMap<String, Object>();

    @JsonDeserialize(using = RealmListNYTimesMultimediumDeserializer.class)
    @JsonProperty("multimedia")
    public RealmList<NYTimesMultimedium> multimedia = new RealmList<NYTimesMultimedium>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.mAdditionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.mAdditionalProperties.put(name, value);
    }
}