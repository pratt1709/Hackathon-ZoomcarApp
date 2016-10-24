package com.prateek.zoomcar.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by prateek.kesarwani on 9/13/2015.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Require to initialize again because it would be unregistered in onStop.
        // And we cannot register in onStart, then won't be able to use in onCreate.
        // So, essentially after onStop, onCreate or onRestart is called and not both. So nice way for now.
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
