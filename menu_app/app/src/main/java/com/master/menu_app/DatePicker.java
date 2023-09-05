package com.master.menu_app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DatePicker extends AppCompatActivity implements
        View.OnClickListener {

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    Button resutl;
    private int mYear, mMonth, mDay, mHour, mMinute,age,cyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        TextView agetext = findViewById(R.id.age);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);
        resutl = findViewById(R.id.Submit);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        resutl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DatePicker.this,MainActivity.class);
                age = 2022-cyear;


                Toast.makeText(DatePicker.this, "Your Age is " + age, Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(android.widget.DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            cyear = year;
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);





                        }
                    },
                    mYear,
                    mMonth,
                    mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}