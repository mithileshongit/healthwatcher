package com.maitkon.healthwatcher;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Primary extends AppCompatActivity {

    private String user;
    private int p;
    private String uname, email;
    private TextView username;
    public com.maitkon.healthwatcher.user user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView HeartRate = (ImageView) this.findViewById(R.id.HR);
        ImageView BloodPressure = (ImageView) this.findViewById(R.id.BP);
        ImageView Ox2 = (ImageView) this.findViewById(R.id.O2);
        ImageView RRate = (ImageView) this.findViewById(R.id.RR);
        ImageView VitalSigns = (ImageView) this.findViewById(R.id.VS);
       // username = (TextView) findViewById(R.id.uname);
        // ImageButton Abt = (ImageButton)this.findViewById(R.id.About);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            //The key argument here must match that used in the other activity
        }
        getUserDetails();


       /* Abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),AboutApp.class);
                startActivity(i);
                finish();
            }
        });*/


        //Every Test Button sends the username + the test number, to go to the wanted test after the instructions activity
        HeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 1;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        BloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 2;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        RRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 3;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

        Ox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 4;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();

            }
        });

        VitalSigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = 5;
                Intent i = new Intent(v.getContext(), StartVitalSigns.class);
                i.putExtra("Usr", user);
                i.putExtra("Page", p);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        Primary.super.onBackPressed();
                        finish();
                        System.exit(0);
                    }
                }).create().show();
    }

    public void getUserDetails() {

       /* com.healthwatcher.user user1 = null;
       uname= user1.getUsername();
       email=user1.getemail();
       username.setText(uname);*/

       /* SharedPreferences preferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        uname = preferences.getString("username", null);
        email = preferences.getString("email", null);
        username.setText(uname);*/



    }


}

