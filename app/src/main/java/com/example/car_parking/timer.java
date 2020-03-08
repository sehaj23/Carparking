package com.example.car_parking;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class timer extends AppCompatActivity{
    private static TextView countdownTimerText,carnumber,slot,entrytime,hours;
    SqliteHelper sql;
    Date date1,date2;
    private  static  String time;

    private static CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        countdownTimerText = (TextView) findViewById(R.id.countdownText);
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
       time = sharedPreferences.getString("validity", null);

        String entry = sharedPreferences.getString("time", null);
        String slott = sharedPreferences.getString("slot", null);


        slot = (TextView) findViewById(R.id.slot);
        entrytime = (TextView) findViewById(R.id.entrytime);
        hours = (TextView) findViewById(R.id.hours);
        hours.setText(time);
        slot.setText(slott);

        entrytime.setText(entry);

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        String currenttime = hour + ":" + minute;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        try {
             date1 = simpleDateFormat.parse(currenttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
             date2 = simpleDateFormat.parse(entry);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long difference = date2.getTime() - date1.getTime();
        int days = (int) (difference / (1000 * 60 * 60 * 24));
       int hourss = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
       int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hourss)) / (1000*60);
       Toast.makeText(timer.this,String.valueOf(difference),Toast.LENGTH_LONG).show();
        if (currenttime == entry)  {
           int startminutes = Integer.parseInt(time) * 60 * 1000 * 60;
        //    startTimer(startminutes);
            startTimer(startminutes);

        } else {

            startTimerforparking(min);

            Toast.makeText(timer.this,"Your Bookings starts at "+ entry,Toast.LENGTH_LONG).show();

        }

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
    private void startTimerforparking(int timeleft) {

        countDownTimer = new CountDownTimer(timeleft, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                countdownTimerText.setText(hms);//set text
            }

            public void onFinish() {
                int noOfminutes = Integer.parseInt(time) * 60 * 1000 * 60;
                startTimer(noOfminutes);

//                sql.timeover();
                //   startTimer.setText(getString(R.string.start_timer));//Change button text

            }
        }.start();

    }


}
