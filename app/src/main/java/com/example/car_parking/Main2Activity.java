package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.*;

public class Main2Activity extends AppCompatActivity {
    Button datee,time,submit;
    RadioGroup type;
    EditText hours;
    TextView location;
    RadioButton radioButton;
    SqliteHelper sqliteHelper;
    SharedPreferences sharedPreferences;
    String car_type,final_time,final_date;
    int start_hours;
    int start_minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main2);
        datee = (Button)findViewById(R.id.date);
        time = (Button)findViewById(R.id.textview12);
        submit = (Button)findViewById(R.id.submit);
        type = (RadioGroup) findViewById(R.id.type);
        hours = (EditText)findViewById(R.id.hours);
        location= (TextView)findViewById(R.id.location);

        location.setText("PAVILION MALL PUNE");


        datee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                final int mYear = mcurrentDate.get(Calendar.YEAR);
                final int mMonth = mcurrentDate.get(Calendar.MONTH);
                final int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;


                mDatePicker = new DatePickerDialog(Main2Activity.this, new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;

                        datee.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        final_date = selectedday + "/" + selectedmonth + "/" + selectedyear;


                    }
                }, mYear, mMonth, mDay);

                mDatePicker.setTitle("Select Date");
                mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mDatePicker.show();
            }
        });


        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Main2Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                        final_time = selectedHour + ":" + selectedMinute;
                        start_hours =selectedHour;
                        start_minute = selectedMinute;

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hours.length() == 0){
                    makeText(Main2Activity.this,"Please Enter Number of Hours", LENGTH_LONG).show();

                }else {
                    String valid = hours.getText().toString();



                    if(type.getCheckedRadioButtonId() == -1){
                       Toast.makeText(Main2Activity.this,"Please Choose The Type", LENGTH_LONG).show();
                    }else {


                        int selectedId = type.getCheckedRadioButtonId();
                        int endtime = start_hours + Integer.parseInt(valid);
                        String final_end_time = String.valueOf(endtime) + ":" + String.valueOf(start_minute);

                        //     Toast.makeText(Main2Activity.this,String.valueOf(final_end_time),Toast.LENGTH_LONG).show();


                        // find the radiobutton by returned id


                        radioButton = (RadioButton) findViewById(selectedId);


//                    Toast.makeText(Main2Activity.this,
                        //                          radioButton.getText(), Toast.LENGTH_SHORT).show();

                        car_type = radioButton.getText().toString();

                        sharedPreferences = Main2Activity.this.getSharedPreferences("MyPrefs",
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("date", final_date);
                        editor.putString("time", final_time);
                        editor.putString("validity", valid);
                        editor.putString("type", car_type);
                        editor.putString("endtime", final_end_time);
                        editor.commit();

                        Intent intent = new Intent(Main2Activity.this, Parking.class);
                        startActivity(intent);
                    }

                }

            }
        });


        //  sqliteHelper.addcurrentbooking(email,datee.toString(),time.toString());

    }
}
