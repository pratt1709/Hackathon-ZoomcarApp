package com.prateek.zoomcar.task;

import android.content.Context;
import android.util.Log;

import com.prateek.zoomcar.event.CarListEvent;
import com.prateek.zoomcar.event.FailureEvent;
import com.prateek.zoomcar.rest.ApiHitsResponseDto;
import com.prateek.zoomcar.rest.ListCarsResponseDto;
import com.prateek.zoomcar.rest.ZoomcarService;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by prateek.kesarwani on 8/29/2015.
 */
public class GetCarListingTask {

    Context mApplicationContext;

    private GetCarListingTask(Context applicationContext) {
        this.mApplicationContext = applicationContext;
    }

    public static GetCarListingTaskBuilder newTask() {
        return new GetCarListingTaskBuilder();
    }

    public static class GetCarListingTaskBuilder {

        // As these are properties(sets some content and returns self), so not using standard getters/setters
        public GetCarListingTaskBuilder subscriberId() {
            return this;
        }

        public GetCarListingTask build(Context applicationContext) {
            return new GetCarListingTask(applicationContext);
        }
    }

    public void execute() {
        fetchCars();
    }

    private CarListEvent mCarListEvent;

    private void fetchCars() {
        ZoomcarService.Implementation.get().getCarListing(new Callback<ListCarsResponseDto>() {
            @Override
            public void success(ListCarsResponseDto listCarsResponseDto, Response response) {
                Log.e("Success", "Success");
                mCarListEvent = new CarListEvent();
                mCarListEvent.setListCarsResponseDto(listCarsResponseDto);

                // Also fetch 'Api Hits' in here, as its required in HomePage itself
                fetchApiHits();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Failure", "Failure");
                EventBus.getDefault().post(new FailureEvent());
            }
        });
    }

    private void fetchApiHits() {
        ZoomcarService.Implementation.get().getApiHits(new Callback<ApiHitsResponseDto>() {
            @Override
            public void success(ApiHitsResponseDto apiHitsResponseDto, Response response) {
                Log.e("Success", "Success");

                if (mCarListEvent != null) {
                    mCarListEvent.setApiHitsResponseDto(apiHitsResponseDto);
                    EventBus.getDefault().post(mCarListEvent);
                } else {
                    EventBus.getDefault().post(new FailureEvent());
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Failure", "Failure");
                EventBus.getDefault().post(new FailureEvent());
            }
        });
    }
}