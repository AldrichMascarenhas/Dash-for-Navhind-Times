
package com.dashfornavhindtimes.data.model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@org.parceler.Parcel
public class Links {

    @SerializedName("self")
    @Expose
    public List<Self> self = null;
    @SerializedName("collection")
    @Expose
    public List<Collection> collection = null;
    @SerializedName("about")
    @Expose
    public List<About> about = null;
    @SerializedName("author")
    @Expose
    public List<Author> author = null;
    @SerializedName("replies")
    @Expose
    public List<Reply> replies = null;
    @SerializedName("version-history")
    @Expose
    public List<VersionHistory> versionHistory = null;
    @SerializedName("wp:attachment")
    @Expose
    public List<WpAttachment> wpAttachment = null;
    @SerializedName("wp:term")
    @Expose
    public List<WpTerm> wpTerm = null;
    @SerializedName("curies")
    @Expose
    public List<Cury> curies = null;
    @SerializedName("wp:featuredmedia")
    @Expose
    public List<WpFeaturedmedium> wpFeaturedmedia = null;

    public List<Self> getSelf() {
        return self;
    }

    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<VersionHistory> getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(List<VersionHistory> versionHistory) {
        this.versionHistory = versionHistory;
    }

    public List<WpAttachment> getWpAttachment() {
        return wpAttachment;
    }

    public void setWpAttachment(List<WpAttachment> wpAttachment) {
        this.wpAttachment = wpAttachment;
    }

    public List<WpTerm> getWpTerm() {
        return wpTerm;
    }

    public void setWpTerm(List<WpTerm> wpTerm) {
        this.wpTerm = wpTerm;
    }

    public List<Cury> getCuries() {
        return curies;
    }

    public void setCuries(List<Cury> curies) {
        this.curies = curies;
    }

    public List<WpFeaturedmedium> getWpFeaturedmedia() {
        return wpFeaturedmedia;
    }

    public void setWpFeaturedmedia(List<WpFeaturedmedium> wpFeaturedmedia) {
        this.wpFeaturedmedia = wpFeaturedmedia;
    }

}
