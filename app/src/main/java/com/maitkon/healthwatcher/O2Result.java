package com.maitkon.healthwatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class O2Result extends AppCompatActivity {

    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    java.util.Date today = Calendar.getInstance().getTime();
    int O2;
    Toolbar toolbar;
    private String user, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o2_result);

        Date = df.format(today);
        TextView RO2 = (TextView) this.findViewById(R.id.O2R);
        // ImageButton SO2 = (ImageButton) this.findViewById(R.id.SendO2);
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
            O2 = bundle.getInt("O2R");
            user = bundle.getString("Usr");
            RO2.setText(String.valueOf(O2));
        }
       /* SO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Health Watcher");
                i.putExtra(Intent.EXTRA_TEXT, user + "'s Oxygen Saturation Level " + "\n" + " at " + Date + " is :   " + O2);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(O2Result.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(O2Result.this, Primary.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
        super.onBackPressed();

    }
}
