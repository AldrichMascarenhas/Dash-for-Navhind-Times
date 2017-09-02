
package com.dashfornavhindtimes.data.model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@org.parceler.Parcel
public class Cury {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("href")
    @Expose
    public String href;
    @SerializedName("templated")
    @Expose
    public Boolean templated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }

}
