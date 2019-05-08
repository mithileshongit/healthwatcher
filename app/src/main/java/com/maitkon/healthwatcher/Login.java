package com.maitkon.healthwatcher;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    public Button Log;
    public TextInputEditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    public Spinner GenderSpin;
    public String m1 = "Male";
    public String m2 = "Female";
    public String nameStr, weightStr, heightStr, ageStr, passStr, usrStr, usrStrlow, passConStr, emailStr;
    UserDB Data = new UserDB(this);
    UserDB check = new UserDB(this);
    int c, y = 0;
    int check1 = 0;
    private Button signin;
    private Toast mainToast;
    private int age, weight, height;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Log = (Button) findViewById(R.id.Login);
        ed1 = (TextInputEditText) findViewById(R.id.edth);
        ed2 = (TextInputEditText) findViewById(R.id.edtw);
        ed3 = (TextInputEditText) findViewById(R.id.edtn);
        ed4 = (TextInputEditText) findViewById(R.id.edta);
        ed5 = (TextInputEditText) findViewById(R.id.edtu);
        ed6 = (TextInputEditText) findViewById(R.id.edtp);
        ed7 = (TextInputEditText) findViewById(R.id.edtpc);
        ed8 = (TextInputEditText) findViewById(R.id.edte);
        signin = (Button) findViewById(R.id.signin);
        // GenderSpin = (Spinner) findViewById(R.id.SGender);


        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
*/
/*
        adapter.setDropDownViewResource(R.layout.spinner_item);
*/
        //  spinner.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.white), PorterDuff.Mode.SRC_ATOP);
        //  GenderSpin.getBackground().setColorFilter(ContextCompat.getColor(this,R.color.colorAccent), PorterDuff.Mode.SRC);
        //GenderSpin.setAdapter(adapter);
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1 = 0;
                heightStr = ed1.getText().toString();
                weightStr = ed2.getText().toString();
                //nameStr = ed3.getText().toString();
                ageStr = ed4.getText().toString();
                usrStrlow = ed5.getText().toString();
                passStr = ed6.getText().toString();
                passConStr = ed7.getText().toString();
                emailStr = ed8.getText().toString();
                usrStr = usrStrlow.toLowerCase();

                c = check.checkUser(usrStr); //will check if username exists will return 0 otherwise it will be 1

                if (usrStr.length() < 3 || usrStr.length() > 20) {
                    check1 = 1;
                    mainToast = Toast.makeText(getApplicationContext(), "Username length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                }

                if (c == y) {
                    check1 = 1;
                    mainToast = Toast.makeText(getApplicationContext(), "Username already exist", Toast.LENGTH_SHORT);
                    mainToast.show();

                }

                if (!(passStr.equals(passConStr))) {
                    check1 = 1;
                    mainToast = Toast.makeText(getApplicationContext(), "Password don't match !", Toast.LENGTH_SHORT);
                    mainToast.show();
                }

                if (passStr.length() < 3 || passStr.length() > 20) {
                    check1 = 1;
                    mainToast = Toast.makeText(getApplicationContext(), "Password length must be between 3-20 characters", Toast.LENGTH_SHORT);
                    mainToast.show();
                }

                if (ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || passStr.isEmpty() || passConStr.isEmpty() || emailStr.isEmpty() || usrStr.isEmpty()) {
                    check1 = 1;
                    mainToast = Toast.makeText(getApplicationContext(), "Please fill all your data ", Toast.LENGTH_SHORT);
                    mainToast.show();
                } else if (check1 == 0) {
                    check1 = 0;
                    age = Integer.parseInt(ageStr);
                    weight = Integer.parseInt(weightStr);
                    height = Integer.parseInt(heightStr);
                  /*  String text = GenderSpin.getSelectedItem().toString();
                    int k = 0;

                    if (text.equals(m1)) //If gender is male K = 1
                        k = 1;
                    if (text.equals(m2)) //If gender is female K = 2
                        k = 2;*/
                    user per = new user();
                    per.setUsername(usrStr);
                    //  per.setname(nameStr);
                    per.setage(age);
                    per.setemail(emailStr);
                    per.setPass(passStr);
                    per.setheight(height);
                    per.setweight(weight);
                    //    per.setgender(k);
                    Data.addUser(per);
                    Intent i = new Intent(v.getContext(), First.class);
                    mainToast = Toast.makeText(getApplicationContext(), "Your account has been created", Toast.LENGTH_SHORT);
                    mainToast.show();
                    startActivity(i);
                    finish();

                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), First.class);
                startActivity(intent);
            }
        });
    }
}

