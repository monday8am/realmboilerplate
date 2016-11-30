package com.monday8am.realmboilerplate.data.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/*
 * NYTimesMultimedium API object used as POJO and as a Realm object
 * at the same time.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "url",
        "format",
        "height",
        "width",
        "type",
        "subtype",
        "caption",
        "copyright"
        })
public class NYTimesMultimedium extends RealmObject {
    @PrimaryKey
    @JsonProperty("url")
    public String url;
    @JsonProperty("format")
    public String format;
    @JsonProperty("height")
    public int height;
    @JsonProperty("width")
    public int width;
    @JsonProperty("type")
    public String type;
    @JsonProperty("subtype")
    public String subtype;
    @JsonProperty("caption")
    public String caption;
    @JsonProperty("copyright")
    public String copyright;

    @Ignore
    @JsonIgnore
    private Map<String, Object> mAdditionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.mAdditionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.mAdditionalProperties.put(name, value);
    }
}
