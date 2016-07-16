package com.marlonjones.safeguard;

import android.app.IntentService;
import android.content.Intent;

public class alerterservice extends IntentService {
    public alerterservice() {
        super(null);

    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        String dataString = workIntent.getDataString();
    }
}