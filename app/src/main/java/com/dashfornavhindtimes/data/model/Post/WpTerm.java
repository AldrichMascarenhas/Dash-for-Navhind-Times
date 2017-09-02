
package com.dashfornavhindtimes.data.model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@org.parceler.Parcel
public class WpTerm {

    @SerializedName("taxonomy")
    @Expose
    public String taxonomy;
    @SerializedName("embeddable")
    @Expose
    public Boolean embeddable;
    @SerializedName("href")
    @Expose
    public String href;

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
