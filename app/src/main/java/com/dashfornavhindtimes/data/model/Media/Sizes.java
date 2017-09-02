
package com.dashfornavhindtimes.data.model.Media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sizes {

    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("medium_large")
    @Expose
    private MediumLarge mediumLarge;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("tie-small")
    @Expose
    private TieSmall tieSmall;
    @SerializedName("tie-medium")
    @Expose
    private TieMedium tieMedium;
    @SerializedName("tie-large")
    @Expose
    private TieLarge tieLarge;
    @SerializedName("slider")
    @Expose
    private Slider slider;
    @SerializedName("big-slider")
    @Expose
    private BigSlider bigSlider;
    @SerializedName("full")
    @Expose
    private Full full;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public MediumLarge getMediumLarge() {
        return mediumLarge;
    }

    public void setMediumLarge(MediumLarge mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    public Large getLarge() {
        return large;
    }

    public void setLarge(Large large) {
        this.large = large;
    }

    public TieSmall getTieSmall() {
        return tieSmall;
    }

    public void setTieSmall(TieSmall tieSmall) {
        this.tieSmall = tieSmall;
    }

    public TieMedium getTieMedium() {
        return tieMedium;
    }

    public void setTieMedium(TieMedium tieMedium) {
        this.tieMedium = tieMedium;
    }

    public TieLarge getTieLarge() {
        return tieLarge;
    }

    public void setTieLarge(TieLarge tieLarge) {
        this.tieLarge = tieLarge;
    }

    public Slider getSlider() {
        return slider;
    }

    public void setSlider(Slider slider) {
        this.slider = slider;
    }

    public BigSlider getBigSlider() {
        return bigSlider;
    }

    public void setBigSlider(BigSlider bigSlider) {
        this.bigSlider = bigSlider;
    }

    public Full getFull() {
        return full;
    }

    public void setFull(Full full) {
        this.full = full;
    }

}
