package com.master.cia_01;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ThankyouPage  extends AppCompatActivity {
    Button register;
    EditText name,email,mobile,college;
    SharedPreferences sharedPreferences;
    private final static String shared_pref_name = "myperfname";
    private final static String shared_name_key ="name";
    private final static String shared_email_key ="email";
    private final static  String shared_mobile_key = "mobile";
    private final static String shared_college_key = "collegename";

    boolean checkallfield = false;
    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_thnakyoupage);
        register = findViewById(R.id.Submit_button);
        name = findViewById(R.id.name_text);
        email = findViewById(R.id.email_text);
        mobile = findViewById(R.id.mobile_text);
        college = findViewById(R.id.College_Name_text);
        sharedPreferences =getSharedPreferences(shared_pref_name,MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkallfield = checkmated();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(shared_email_key,email.getText().toString());
                editor.putString(shared_name_key,name.getText().toString());
                editor.putString(shared_college_key,college.getText().toString());
                editor.putString(shared_mobile_key,mobile.getText().toString());
                editor.apply();
                if(checkallfield) {
                    Toast.makeText(ThankyouPage.this, "Submitted Successfully,Thank You", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ThankyouPage.this,StoredPerfernceActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    protected boolean checkmated() {
        if (email.length() == 0) {
            email.setError("Please enter the text");
            return false ;
        }
        if(name.length() ==0) {
            name.setError("please enter the name");
            return false ;
        }
        if(college.length() ==0 ){
            college.setError("please enter the college name");
            return false;
        }
        if(mobile.length() == 0) {
            mobile.setError("please enter the mobile number");
            return false;
        }

        return true;
    }
}
