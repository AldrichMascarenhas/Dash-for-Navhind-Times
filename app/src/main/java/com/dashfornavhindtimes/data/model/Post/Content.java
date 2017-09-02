
package com.dashfornavhindtimes.data.model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@org.parceler.Parcel
public class Content {

    @SerializedName("rendered")
    @Expose
    public String rendered;
    @SerializedName("protected")
    @Expose
    public Boolean _protected;

    public String getRendered() {
        //TODO :Better way

        return rendered.substring(0, rendered.indexOf("<div"));
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Boolean getProtected() {
        return _protected;
    }

    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

}
