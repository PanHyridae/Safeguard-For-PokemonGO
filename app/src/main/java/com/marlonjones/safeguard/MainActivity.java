package com.marlonjones.safeguard;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Safeguard Service is now on!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //These are the various buttons on the screen! ^_^
        //-------------------------------------------------------------
        //This first button checks and sees if Pokemon GO is installed.
        Button buttonCheck = (Button) findViewById(R.id.buttoncheck);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If Pokemon GO is installed, you can open it with this button!
                boolean installed = appcheck ("com.nianticlabs.pokemongo");
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
    }

    //This is the code used for the Package Manager for the buttons.
    //Found on StackOverflow from
    //http://stackoverflow.com/questions/11392183/how-to-check-programmatically-if-an-application-is-installed-or-not-in-android
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
