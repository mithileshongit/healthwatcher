package com.maitkon.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RespirationResult extends AppCompatActivity {

    int RR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    Toolbar toolbar;
    private String user, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respiration_result);

        Date = df.format(today);
        TextView RRR = (TextView) this.findViewById(R.id.RRR);
        // ImageButton SRR = (ImageButton) this.findViewById(R.id.SendRR);
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
            RR = bundle.getInt("bpm");
            user = bundle.getString("Usr");
            RRR.setText(String.valueOf(RR));
        }

        /*SRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
                i.putExtra(Intent.EXTRA_TEXT, user + "'s Respiration Rate " + "\n" + " at " + Date + " is :  " + RR);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RespirationResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(RespirationResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
