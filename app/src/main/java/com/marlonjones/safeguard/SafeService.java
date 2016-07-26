package com.marlonjones.safeguard;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.afollestad.materialdialogs.MaterialDialog;

public class SafeService extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
            //Lollipop or Above
        if (Build.VERSION.SDK_INT >= 21) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
            new NotificationCompat.Builder(MainActivity.this);
            builder.setSmallIcon(R.drawable.smallplaceholder);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            builder.setContentTitle("Safeguard");
            builder.setContentText("Careful, Trainer! Be sure to look up and stay aware of your surroundings!");
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Careful, Trainer! Be sure to look up and stay aware of your surroundings!"));
            builder.setPriority(Notification.PRIORITY_HIGH);
            builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(NOTIFICATION_ID, builder.build());
            //Below Lollipop
        } else {
            new MaterialDialog.Builder(Context)
                    .title(R.string.warning_title)
                    .content(R.string.warning)
                    .positiveText(R.string.button_ok)
                    .show();
        }

    }
}