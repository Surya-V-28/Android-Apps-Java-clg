package com.master.cia_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class StoredPerfernceActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private final static String shared_pref_name = "myperfname";
    private final static String shared_name_key ="name";
    private final static String shared_email_key ="email";
    private final static  String shared_mobile_key = "mobile";
    private final static String shared_college_key = "collegename";
   TextView name_perf,college_perf,email_perf,mobile_perf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_perfernce);
        name_perf = findViewById(R.id.name_perf);
        college_perf = findViewById(R.id.college_perf);
        email_perf = findViewById(R.id.email_perf);
        mobile_perf = findViewById(R.id.mobile_pref);
        sharedPreferences = getSharedPreferences(shared_pref_name,MODE_PRIVATE);
        String name_got = sharedPreferences.getString(shared_name_key,"nonamegot");
        String email_got = sharedPreferences.getString(shared_email_key,"noemailgot");
        String college_got = sharedPreferences.getString(shared_college_key,"nocollege");
        String mobile_got = sharedPreferences.getString(shared_mobile_key,"nombile");

        name_perf.setText(name_got);
        email_perf.setText(email_got);
        college_perf.setText(college_got);
        mobile_perf.setText(mobile_got);

    }
}