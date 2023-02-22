package com.e.gasserviceapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PostPropertyModel implements Serializable {

    @SerializedName("typeland")
    @Expose
    private String typeland;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("property_for")
    @Expose
    private String propertyFor;
    @SerializedName("bedrooms")
    @Expose
    private String bedrooms;
    @SerializedName("bathrooms")
    @Expose
    private String bathrooms;
    @SerializedName("furnished")
    @Expose
    private String furnished;
    @SerializedName("builtuparea")
    @Expose
    private String builtuparea;
    @SerializedName("carpetarea")
    @Expose
    private String carpetarea;
    @SerializedName("constr_status")
    @Expose
    private String constrStatus;
    @SerializedName("rerid")
    @Expose
    private String reraId;
    @SerializedName("rentunit")
    @Expose
    private String rentunit ;
    @SerializedName("landmark")
    @Expose
    private String landmark;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("nooffloors")
    @Expose
    private String nooffloors;
    @SerializedName("floornumber")
    @Expose
    private String floornumber;
    @SerializedName("totalprice")
    @Expose
    private String totalprice;
    @SerializedName("totpricetype")
    @Expose
    private String totpricetype;
    @SerializedName("unitpricetype")
    @Expose
    private String unitpricetype;
    @SerializedName("booking_amt")
    @Expose
    private String bookingAmt;
    @SerializedName("agent_comission")
    @Expose
    private String agentComission;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("brochure")
    @Expose
    private String brochure;
    @SerializedName("balconies")
    @Expose
    private String balconies;
    @SerializedName("nameoftheproject")
    @Expose
    private String nameoftheproject;
    @SerializedName("plotlenth")
    @Expose
    private String plotlenth;
    @SerializedName("plotwidth")
    @Expose
    private String plotwidth;
    @SerializedName("plotunittype")
    @Expose
    private String plotunittype;
    @SerializedName("cornerplot")
    @Expose
    private String cornerplot;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("facing")
    @Expose
    private String facing;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("window_type")
    @Expose
    private String window_type;
    @SerializedName("city_village")
    @Expose
    private String city_village;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("unitprices")
    @Expose
    private String unitprices;
    @SerializedName("projectdescription")
    @Expose
    private String projectdescription;
    @SerializedName("layout")
    @Expose
    private String layout;
    @SerializedName("hilights")
    @Expose
    private String hilights;
    @SerializedName("specifications")
    @Expose
    private String specifications;
    @SerializedName("projectinfo")
    @Expose
    private String projectinfo;
    @SerializedName("aboutdeveloper")
    @Expose
    private String aboutdeveloper;
    @SerializedName("buildername")
    @Expose
    private String buildername;
    @SerializedName("lrs")
    @Expose
    private String lrs;
    @SerializedName("age")
    @Expose
    private String age ;
    @SerializedName("flooring")
    @Expose
    private String flooring;
    @SerializedName("comm_space")
    @Expose
    private String commSpace;
    @SerializedName("totarea_acres")
    @Expose
    private String totareaAcres;
    @SerializedName("totarea_lenth_acres")
    @Expose
    private String totareaLenthAcres;
    @SerializedName("api_key")
    @Expose
    private String apiKey;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("buildunits")
    @Expose
    private String buildunits;
    @SerializedName("maintenance_charges")
    @Expose
    private String maintenance_charges;
    @SerializedName("unit_cost")
    @Expose
    private String unitCost;
    @SerializedName("unit_costtype")
    @Expose
    private String unitCosttype;
    @SerializedName("unittype")
    @Expose
    private String unittype;
    @SerializedName("callforprice")
    @Expose
    private String callforprice;
    @SerializedName("files")
    @Expose
    private ArrayList<String> files;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("amenities")
    @Expose
    private String amenities;
    @SerializedName("contactpersonname")
    @Expose
    private String contactpersonname;
    @SerializedName("contactnumber")
    @Expose
    private String contactnumber;
    @SerializedName("possession_by")
    @Expose
    private String possession_by;
    @SerializedName("unitDetails")
    @Expose
    private ArrayList<UnitDetails> unitDetails;

    public String getWindow_type() {
        return window_type;
    }

    public void setWindow_type(String window_type) {
        this.window_type = window_type;
    }

    public String getMaintenance_charges() {
        return maintenance_charges;
    }

    public void setMaintenance_charges(String maintenance_charges) {
        this.maintenance_charges = maintenance_charges;
    }

    public String getPlotunittype() {
        return plotunittype;
    }

    public void setPlotunittype(String plotunittype) {
        this.plotunittype = plotunittype;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getReraId() {
        return reraId;
    }

    public void setReraId(String reraId) {
        this.reraId = reraId;
    }

    public String getRentunit() {
        return rentunit;
    }

    public void setRentunit(String rentunit) {
        this.rentunit = rentunit;
    }

    public String getPossession_by() {
        return possession_by;
    }

    public void setPossession_by(String possession_by) {
        this.possession_by = possession_by;
    }

    public String getUnitpricetype() {
        return unitpricetype;
    }

    public void setUnitpricetype(String unitpricetype) {
        this.unitpricetype = unitpricetype;
    }

    public String getCity_village() {
        return city_village;
    }

    public void setCity_village(String city_village) {
        this.city_village = city_village;
    }

    public String getContactpersonname() {
        return contactpersonname;
    }

    public void setContactpersonname(String contactpersonname) {
        this.contactpersonname = contactpersonname;
    }

    public String getTypeland() {
        return typeland;
    }

    public void setTypeland(String typeland) {
        this.typeland = typeland;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getBuildername() {
        return buildername;
    }

    public void setBuildername(String buildername) {
        this.buildername = buildername;
    }

    public ArrayList<UnitDetails> getUnitDetails() {
        return unitDetails;
    }

    public void setUnitDetails(ArrayList<UnitDetails> unitDetails) {
        this.unitDetails = unitDetails;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String user_id) {
        this.userId = user_id;
    }

    public String getProperty_type() {
        return propertyType;
    }

    public void setProperty_type(String property_type) {
        this.propertyType = property_type;
    }

    public String getProperty_for() {
        return propertyFor;
    }

    public void setProperty_for(String property_for) {
        this.propertyFor = property_for;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getFurnished() {
        return furnished;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public String getBuiltuparea() {
        return builtuparea;
    }

    public void setBuiltuparea(String builtuparea) {
        this.builtuparea = builtuparea;
    }

    public String getCarpetarea() {
        return carpetarea;
    }

    public void setCarpetarea(String carpetarea) {
        this.carpetarea = carpetarea;
    }

    public String getConstr_status() {
        return constrStatus;
    }

    public void setConstr_status(String constr_status) {
        this.constrStatus = constr_status;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getDistrict_id() {
        return districtId;
    }

    public void setDistrict_id(String district_id) {
        this.districtId = district_id;
    }

    public String getState_id() {
        return stateId;
    }

    public void setState_id(String state_id) {
        this.stateId = state_id;
    }

    public String getCity_id() {
        return cityId;
    }

    public void setCity_id(String city_id) {
        this.cityId = city_id;
    }

    public String getNooffloors() {
        return nooffloors;
    }

    public void setNooffloors(String nooffloors) {
        this.nooffloors = nooffloors;
    }

    public String getFloornumber() {
        return floornumber;
    }

    public void setFloornumber(String floornumber) {
        this.floornumber = floornumber;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getTotpricetype() {
        return totpricetype;
    }

    public void setTotpricetype(String totpricetype) {
        this.totpricetype = totpricetype;
    }

    public String getBooking_amt() {
        return bookingAmt;
    }

    public void setBooking_amt(String booking_amt) {
        this.bookingAmt = booking_amt;
    }

    public String getAgent_comission() {
        return agentComission;
    }

    public void setAgent_comission(String agent_comission) {
        this.agentComission = agent_comission;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public String getBalconies() {
        return balconies;
    }

    public void setBalconies(String balconies) {
        this.balconies = balconies;
    }

    public String getNameoftheproject() {
        return nameoftheproject;
    }

    public void setNameoftheproject(String nameoftheproject) {
        this.nameoftheproject = nameoftheproject;
    }

    public String getPlotlenth() {
        return plotlenth;
    }

    public void setPlotlenth(String plotlenth) {
        this.plotlenth = plotlenth;
    }

    public String getPlotwidth() {
        return plotwidth;
    }

    public void setPlotwidth(String plotwidth) {
        this.plotwidth = plotwidth;
    }

    public String getCornerplot() {
        return cornerplot;
    }

    public void setCornerplot(String cornerplot) {
        this.cornerplot = cornerplot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacing() {
        return facing;
    }

    public void setFacing(String facing) {
        this.facing = facing;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUnitprices() {
        return unitprices;
    }

    public void setUnitprices(String unitprices) {
        this.unitprices = unitprices;
    }

    public String getProjectdescription() {
        return projectdescription;
    }

    public void setProjectdescription(String projectdescription) {
        this.projectdescription = projectdescription;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getHilights() {
        return hilights;
    }

    public void setHilights(String hilights) {
        this.hilights = hilights;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getProjectinfo() {
        return projectinfo;
    }

    public void setProjectinfo(String projectinfo) {
        this.projectinfo = projectinfo;
    }

    public String getAboutdeveloper() {
        return aboutdeveloper;
    }

    public void setAboutdeveloper(String aboutdeveloper) {
        this.aboutdeveloper = aboutdeveloper;
    }

    public String getLrs() {
        return lrs;
    }

    public void setLrs(String lrs) {
        this.lrs = lrs;
    }

    public String getFlooring() {
        return flooring;
    }

    public void setFlooring(String flooring) {
        this.flooring = flooring;
    }

    public String getComm_space() {
        return commSpace;
    }

    public void setComm_space(String comm_space) {
        this.commSpace = comm_space;
    }

    public String getTotarea_acres() {
        return totareaAcres;
    }

    public void setTotarea_acres(String totarea_acres) {
        this.totareaAcres = totarea_acres;
    }

    public String getTotarea_lenth_acres() {
        return totareaLenthAcres;
    }

    public void setTotarea_lenth_acres(String totarea_lenth_acres) {
        this.totareaLenthAcres = totarea_lenth_acres;
    }

    public String getApi_key() {
        return apiKey;
    }

    public void setApi_key(String api_key) {
        this.apiKey = api_key;
    }

    public String getProperty_id() {
        return propertyId;
    }

    public void setProperty_id(String property_id) {
        this.propertyId = property_id;
    }

    public String getBuildunits() {
        return buildunits;
    }

    public void setBuildunits(String buildunits) {
        this.buildunits = buildunits;
    }

    public String getUnit_cost() {
        return unitCost;
    }

    public void setUnit_cost(String unit_cost) {
        this.unitCost = unit_cost;
    }

    public String getUnit_costtype() {
        return unitCosttype;
    }

    public void setUnit_costtype(String unit_costtype) {
        this.unitCosttype = unit_costtype;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getVideo_url() {
        return videoUrl;
    }

    public void setVideo_url(String video_url) {
        this.videoUrl = video_url;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyFor() {
        return propertyFor;
    }

    public void setPropertyFor(String propertyFor) {
        this.propertyFor = propertyFor;
    }

    public String getConstrStatus() {
        return constrStatus;
    }

    public void setConstrStatus(String constrStatus) {
        this.constrStatus = constrStatus;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getBookingAmt() {
        return bookingAmt;
    }

    public void setBookingAmt(String bookingAmt) {
        this.bookingAmt = bookingAmt;
    }

    public String getAgentComission() {
        return agentComission;
    }

    public void setAgentComission(String agentComission) {
        this.agentComission = agentComission;
    }

    public String getTotareaAcres() {
        return totareaAcres;
    }

    public void setTotareaAcres(String totareaAcres) {
        this.totareaAcres = totareaAcres;
    }

    public String getTotareaLenthAcres() {
        return totareaLenthAcres;
    }

    public void setTotareaLenthAcres(String totareaLenthAcres) {
        this.totareaLenthAcres = totareaLenthAcres;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    public String getUnitCosttype() {
        return unitCosttype;
    }

    public void setUnitCosttype(String unitCosttype) {
        this.unitCosttype = unitCosttype;
    }

    public String getCallforprice() {
        return callforprice;
    }

    public void setCallforprice(String callforprice) {
        this.callforprice = callforprice;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
