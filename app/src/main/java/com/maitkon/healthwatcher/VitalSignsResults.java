package com.maitkon.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VitalSignsResults extends AppCompatActivity {

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    int VBP1, VBP2, VRR, VHR, VO2;
    Toolbar toolbar;
    private String user, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allresults_page);
        Date = df.format(today);
        TextView VSRR = (TextView) this.findViewById(R.id.RRV);
        TextView VSBPS = (TextView) this.findViewById(R.id.BP2V);
        TextView VSHR = (TextView) this.findViewById(R.id.HRV);
        TextView VSO2 = (TextView) this.findViewById(R.id.O2V);
        //  ImageButton All = (ImageButton) this.findViewById(R.id.SendAll);

       /* toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            VRR = bundle.getInt("breath");
            VHR = bundle.getInt("bpm");
            VBP1 = bundle.getInt("SP");
            VBP2 = bundle.getInt("DP");
            VO2 = bundle.getInt("O2R");
            user = bundle.getString("Usr");
            VSRR.setText(String.valueOf(VRR));
            VSHR.setText(String.valueOf(VHR));
            VSBPS.setText(String.valueOf(VBP1 + " / " + VBP2));
            VSO2.setText(String.valueOf(VO2));
        }

        /*All.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
                i.putExtra(Intent.EXTRA_TEXT, user + "'s new measuerment " + "\n" + " at " + Date + " are :" + "\n" + "Heart Rate = " + VHR + "\n" + "Blood Pressure = " + VBP1 + " / " + VBP2 + "\n" + "Respiration Rate = " + VRR + "\n" + "Oxygen Saturation = " + VO2);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(VitalSignsResults.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(VitalSignsResults.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
    }
}
