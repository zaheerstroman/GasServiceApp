package com.e.gasserviceapp.Response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UnitsResponse {

    @SerializedName("result")
    ArrayList<String> units;

    public ArrayList<String> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<String> units) {
        this.units = units;
    }

}
