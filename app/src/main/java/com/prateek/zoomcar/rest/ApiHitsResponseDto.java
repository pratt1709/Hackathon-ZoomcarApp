package com.prateek.zoomcar.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prateek.kesarwani on 9/13/2015.
 */
public class ApiHitsResponseDto {

    @SerializedName("api_hits")
    String apiHits;

    public String getApiHits() {
        return apiHits;
    }

    public void setApiHits(String apiHits) {
        this.apiHits = apiHits;
    }
}
