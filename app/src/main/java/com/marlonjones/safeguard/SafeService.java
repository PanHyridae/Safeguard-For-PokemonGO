package com.marlonjones.safeguard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Timer;
import java.util.TimerTask;

public class SafeService extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent) {
    //Start 10 - 15 minute service. call startNotification
    }

    public void startNotification() {
        //make notification.
    }

    public void stopNotification() {
        //make notification to end. call .cancel()
    }

    public boolean isNotificationStarted() {
        return true; //THIS IS A PLACEHOLDER
    }

    public interface LocalBinder {
        SafeService getService();
    }
}