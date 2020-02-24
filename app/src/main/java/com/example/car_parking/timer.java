package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class timer extends AppCompatActivity {
    private static TextView countdownTimerText,carnumber,slot,entrytime,hours;
    SqliteHelper sql;

    private static CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        countdownTimerText = (TextView) findViewById(R.id.countdownText);
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
      String time =   sharedPreferences.getString("validity",null);

        String entry =   sharedPreferences.getString("time",null);
        String slott=   sharedPreferences.getString("slot",null);
      int noOfminutes = Integer.parseInt(time)*60*1000*60;

        slot = (TextView)findViewById(R.id.slot);
        entrytime = (TextView)findViewById(R.id.entrytime);
        hours=(TextView)findViewById(R.id.hours);
        hours.setText(time);
        slot.setText(slott);

        entrytime.setText(entry);



        startTimer(noOfminutes);

    }

    private void startTimer(int noOfMinutes) {
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                countdownTimerText.setText(hms);//set text
            }

            public void onFinish() {

                countdownTimerText.setText("TIME'S UP!!"); //On finish change timer text
                countDownTimer = null;//set CountDownTimer to null
               sql.timeover();
             //   startTimer.setText(getString(R.string.start_timer));//Change button text

            }
        }.start();

    }

}
