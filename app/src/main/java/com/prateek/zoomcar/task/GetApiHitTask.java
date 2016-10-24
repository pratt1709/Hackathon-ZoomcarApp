package com.prateek.zoomcar.task;

import android.content.Context;
import android.util.Log;

import com.prateek.zoomcar.event.FailureEvent;
import com.prateek.zoomcar.rest.ApiHitsResponseDto;
import com.prateek.zoomcar.rest.ZoomcarService;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by prateek.kesarwani on 8/29/2015.
 */
public class GetApiHitTask {

    Context mApplicationContext;

    private GetApiHitTask(Context applicationContext) {
        this.mApplicationContext = applicationContext;
    }

    public static GetApiHitTaskBuilder newTask() {
        return new GetApiHitTaskBuilder();
    }

    public static class GetApiHitTaskBuilder {

        // As these are properties(sets some content and returns self), so not using standard getters/setters
        public GetApiHitTaskBuilder subscriberId() {
            return this;
        }

        public GetApiHitTask build(Context applicationContext) {
            return new GetApiHitTask(applicationContext);
        }
    }

    public void execute() {
        ZoomcarService.Implementation.get().getApiHits(new Callback<ApiHitsResponseDto>() {
            @Override
            public void success(ApiHitsResponseDto apiHitsResponseDto, Response response) {
                Log.e("Success", "Success");
                EventBus.getDefault().post(apiHitsResponseDto);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Failure", "Failure");
                EventBus.getDefault().post(new FailureEvent());
            }
        });
    }
}
