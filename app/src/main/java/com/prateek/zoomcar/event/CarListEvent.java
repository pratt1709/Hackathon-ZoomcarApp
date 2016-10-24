package com.prateek.zoomcar.event;

import com.prateek.zoomcar.rest.ApiHitsResponseDto;
import com.prateek.zoomcar.rest.ListCarsResponseDto;

/**
 * Created by prateek.kesarwani on 9/13/2015.
 */
public class CarListEvent {

    private ApiHitsResponseDto apiHitsResponseDto;
    private ListCarsResponseDto listCarsResponseDto;

    public ApiHitsResponseDto getApiHitsResponseDto() {
        return apiHitsResponseDto;
    }

    public void setApiHitsResponseDto(ApiHitsResponseDto apiHitsResponseDto) {
        this.apiHitsResponseDto = apiHitsResponseDto;
    }

    public ListCarsResponseDto getListCarsResponseDto() {
        return listCarsResponseDto;
    }

    public void setListCarsResponseDto(ListCarsResponseDto listCarsResponseDto) {
        this.listCarsResponseDto = listCarsResponseDto;
    }
}