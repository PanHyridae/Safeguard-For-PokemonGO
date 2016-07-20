package com.marlonjones.safeguard;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {
    public static final int NOTIFICATION_ID = 1;
    final private int REQUEST_PERMISSIONS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Insert permission check here pls. pls. PLS.
        //OnCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*Removed FAB OnClick and Singleton due to fatal errors*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       /* fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSingleton.isNotificationStarted()){
                    mSingleton.startNotification();
                    Snackbar.make(view, "Safeguard is now on!", Snackbar.LENGTH_LONG).show();
                }
                else {
                    mSingleton.stopNotification();
                    Snackbar.make(view, "Safeguard is now off!", Snackbar.LENGTH_LONG).show();
                }
            }
        });*/


        //These are the various buttons on the screen!
        //-------------------------------------------------------------
        //This first button checks and sees if Pokemon GO is installed.
        Button buttonCheck = (Button) findViewById(R.id.buttoncheck);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If Pokemon GO is installed, you can open it with this button!
                boolean installed = appcheck("com.nianticlabs.pokemongo");
                if (installed) {
                    Intent pokemon = getPackageManager()
                            .getLaunchIntentForPackage("com.nianticlabs.pokemongo");
                    startActivity(pokemon);
                }
                //If Pokemon GO is NOT found, it'll throw this error dialog
                else {
                    new MaterialDialog.Builder(MainActivity.this)
                            .title(R.string.error_dialog_title)
                            .content(R.string.error_dialog)
                            .positiveText(R.string.button_ok)
                            .show();
                }
            }
        });

        //This button tests the Dialog portion of the warnings!
        Button buttonTestDialog = (Button) findViewById(R.id.buttontest);
        buttonTestDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(MainActivity.this)
                        .title(R.string.test)
                        .content(R.string.dialog_test)
                        .positiveText(R.string.button_ok)
                        .show();
            }
        });
        /*This button does two things. If the user is running Android Lollipop,
        Marshmallow, Nougat, or anything above that uses the Heads-Up Notifications,
        then they will see a notification. If not, they will see a dialog!*/
        //-------------------------------------------------------------------------
        Button buttonTestNote = (Button) findViewById(R.id.buttontest2);
        buttonTestNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lollipop or Above
                if (Build.VERSION.SDK_INT >= 21) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                            new NotificationCompat.Builder(MainActivity.this);
                                    builder.setSmallIcon(R.drawable.smallplaceholder);
                                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                                    builder.setContentTitle("Test Notification");
                                    builder.setContentText("This is a test of the Heads-Up notification! You will see this every 15 minutes when playing Pokemon GO!");
                    builder.setStyle(new NotificationCompat.BigTextStyle().bigText("This is a test of the Heads-Up notification! You will see this every 15 minutes when playing Pokemon GO!"));
                    builder.setPriority(Notification.PRIORITY_HIGH);
                    builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                    notificationManager.notify(NOTIFICATION_ID, builder.build());
                    //Below Lollipop
                } else {
                    new MaterialDialog.Builder(MainActivity.this)
                            .title(R.string.error_dialog_title)
                            .content(R.string.notification_test_error)
                            .positiveText(R.string.button_ok)
                            .show();
                }

            }
        });
    }

    /*This is the code used for the Package Manager for the buttons.
    Found on StackOverflow from
    http://stackoverflow.com/questions/11392183/how-to-check-programmatically-if-an-application-is-installed-or-not-in-android*/
    private boolean appcheck(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    //Options and Menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
