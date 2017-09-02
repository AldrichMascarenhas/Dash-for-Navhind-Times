
package com.dashfornavhindtimes.data.model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@org.parceler.Parcel
public class WpAttachment {

    @SerializedName("href")
    @Expose
    public String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
