package com.master.cia_01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button register;
    ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic = findViewById(R.id.computer_dep);
        register = findViewById(R.id.Register_Button);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(getApplicationContext(), ThankyouPage.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Loading", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(getApplicationContext(), ThankyouPage.class);
                startActivity(intent);
            }
        });

    }
}