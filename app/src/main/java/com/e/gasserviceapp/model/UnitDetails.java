package com.e.gasserviceapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UnitDetails implements Serializable {
    @SerializedName("name")
    @Expose
    private String unitTitle;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("pricetype")
    @Expose
    private String priceType;
    @SerializedName("priceunits")
    @Expose
    private String priceUnit;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("buildunits")
    @Expose
    private String areaUnits;
    @SerializedName("attachment")
    @Expose
    private String attachment;


    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaUnits() {
        return areaUnits;
    }

    public void setAreaUnits(String areaUnits) {
        this.areaUnits = areaUnits;
    }
}
