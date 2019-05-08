package com.maitkon.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BloodPressureResult extends AppCompatActivity {

    int SP, DP;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    private String user, Date;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_result);
        Date = df.format(today);

        TextView TBP = (TextView) this.findViewById(R.id.BPT);
        //ImageButton SBP = (ImageButton) this.findViewById(R.id.SendBP);
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
            SP = bundle.getInt("SP");
            DP = bundle.getInt("DP");
            user = bundle.getString("Usr");
            TBP.setText(String.valueOf(SP + " / " + DP));
        }

       /* SBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
                i.putExtra(Intent.EXTRA_TEXT, user + "'s Blood Pressure " + "\n" + " at " + Date + " is :    " + SP + " / " + DP);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(BloodPressureResult.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(BloodPressureResult.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
