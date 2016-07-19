package com.marlonjones.safeguard;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.Timer;
import java.util.TimerTask;

/*This code was made with help from
https://xjaphx.wordpress.com/2012/07/07/create-a-service-that-does-a-schedule-task/*/
public class SafeService extends Service {
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    public static final int NOTIFICATION_ID = 1;
    public static final long NOTIFY_INTERVAL = 900 * 1000; // 15 Minutes
    /*^TODO - TEST NOTIFY_INTERVAL FOR ACCURACY^*/

    /*^Those are for the timer and handler so the code
    can recognise it^ The last one gives how long the timer runs */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // Cancels the timer if it already existed.
        if (mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }

    public void startNotification() {
    }

    public void stopNotification() {
    }

    public boolean isNotificationStarted() {
        return false;
    }

    class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // Lollipop or Above
                    if (Build.VERSION.SDK_INT >= 21) {
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(SafeService.this);
                        new NotificationCompat.Builder(SafeService.this);
                        builder.setSmallIcon(R.drawable.smallplaceholder);
                        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                        builder.setContentTitle("Safeguard");
                        builder.setContentText("Be careful, Trainer! Remember to look up and stay aware of your surroundings!!");
                        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Be careful, Trainer! Remember to look up and stay aware of your surroundings!!"));
                        builder.setPriority(Notification.PRIORITY_HIGH);
                        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(NOTIFICATION_ID, builder.build());
                        //Below Lollipop
                    } else {
                        new MaterialDialog.Builder(SafeService.this)
                                .title(R.string.warning_title)
                                .content(R.string.warning)
                                .positiveText(R.string.button_ok)
                                .show();
                    }

                }
            });

                }

            };
        }