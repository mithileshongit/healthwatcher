package com.maitkon.healthwatcher;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;


public class StartVitalSigns extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE};
    int REQUEST_PERMISSION_SETTING = 192;
    AlertDialog alertDialog1;
    private String user;
    private int p;
    private AlertDialog alertDialog, actionWindowdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vital_signs);
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            p = extras.getInt("Page");
        }

        if (checkPermission()) {

        } else {
            requestPermission();
        }
        // takePermission();

        Button VS = (Button) this.findViewById(R.id.StartVS);

        VS.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                switch (p) {

                    case 1: {
                        if (checkPermission()) {
                            Intent i = new Intent(v.getContext(), HeartRateProcess.class);
                            i.putExtra("Usr", user);
                            startActivity(i);
                            finish();

                        } else {
                            requestPermission();
                        }

                    }
                    break;

                    case 2: {
                        if (checkPermission()) {
                            Intent i = new Intent(v.getContext(), BloodPressureProcess.class);
                            i.putExtra("Usr", user);
                            startActivity(i);
                            finish();
                        } else {
                            requestPermission();
                        }
                    }
                    break;

                    case 3: {
                        if (checkPermission()) {
                            Intent i = new Intent(v.getContext(), RespirationProcess.class);
                            i.putExtra("Usr", user);
                            startActivity(i);
                            finish();
                        } else {
                            requestPermission();

                        }

                    }
                    break;

                    case 4: {
                        if (checkPermission()) {
                            Intent i = new Intent(v.getContext(), O2Process.class);
                            i.putExtra("Usr", user);
                            startActivity(i);
                            finish();
                        } else {
                            requestPermission();

                        }
                    }
                    break;

                    case 5: {
                        if (checkPermission()) {


                            Intent i = new Intent(v.getContext(), VitalSignsProcess.class);
                            i.putExtra("Usr", user);
                            startActivity(i);
                            finish();
                        } else {
                            requestPermission();

                        }
                    }


                    break;
                }


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(StartVitalSigns.this, Primary.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

                    // main logic
                } else {

                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    openPermissionSettings();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                                ()
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
                                                // openPermissionSettings();
                                            }
                                        }

                                    });
                        }
                    }
                }
                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("HealthWatcher");
        // builder.setMessage("Allow permission from setting >> Installedapps >> HealthWatcher >> App permissions");
        builder.setMessage("You need to allow access permissions");
        builder.setPositiveButton("OK", okListener);
        builder.setCancelable(false);
        builder.show();


    }


    public void openPermissionSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

}

