package com.maitkon.healthwatcher;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class First extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;

    public static String passStr, usrStr, checkpassStr, usrStrlow, uEmail;
    public Button Meas;
    public Button acc;
    public TextInputEditText ed1, ed2;
    UserDB check = new UserDB(this);
    CheckBox chkRememberMe;
    private Toast mainToast;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        Meas = (Button) findViewById(R.id.prime);
        acc = (Button) findViewById(R.id.newacc);
        ed1 = (TextInputEditText) findViewById(R.id.edtu1);
        ed2 = (TextInputEditText) findViewById(R.id.edtp1);
        chkRememberMe = (CheckBox) findViewById(R.id.checkBoxRemember);
      /*  if (checkPermission()) {
            //main logic or main code

            // . write your main code to execute, It will execute if the permission is already given.

        } else {
            requestPermission();
        }*/
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);


        if (saveLogin == true) {
            ed1.setText(loginPreferences.getString("username", ""));
            ed2.setText(loginPreferences.getString("password", ""));
            chkRememberMe.setChecked(true);
        }

        Meas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usrStrlow = ed1.getText().toString();
                passStr = ed2.getText().toString();
                usrStr = usrStrlow.toLowerCase();


                if (usrStr.length() < 3 || usrStr.length() > 20) {
                    mainToast = Toast.makeText(getApplicationContext(), "Username length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                }
                if (passStr.length() < 3 || passStr.length() > 20) {
                    mainToast = Toast.makeText(getApplicationContext(), "Password length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                } else if (passStr.isEmpty() || usrStr.isEmpty()) {

                    mainToast = Toast.makeText(getApplicationContext(), "Please enter your Username and Password ", Toast.LENGTH_SHORT);
                    mainToast.show();

                } else {

                    checkpassStr = check.checkPass(usrStr);

                    if (passStr.equals(checkpassStr)) {

                        if (chkRememberMe.isChecked()) {
                            loginPrefsEditor.putBoolean("saveLogin", true);
                            loginPrefsEditor.putString("username", usrStr);
                            loginPrefsEditor.putString("password", passStr);
                            loginPrefsEditor.apply();
                        } else {
                            loginPrefsEditor.clear();
                            loginPrefsEditor.commit();
                        }

                        Intent i = new Intent(v.getContext(), Primary.class);
                        i.putExtra("Usr", usrStr);
                        startActivity(i);
                        finish();

                    } else {
                        //Toast something
                        mainToast = Toast.makeText(getApplicationContext(), "Username/Password is incorrect", Toast.LENGTH_SHORT);
                        mainToast.show();
                    }
                }

            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), Login.class);
                startActivity(i);
                finish();

            }
        });

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

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermission();
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
        new AlertDialog.Builder(First.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }


}
