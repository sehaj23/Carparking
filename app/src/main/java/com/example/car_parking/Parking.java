package com.example.car_parking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.car_parking.ui.home.HomeFragment;
import com.google.gson.JsonIOException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


public class Parking extends AppCompatActivity  implements PaymentResultListener {
    ImageView car1,car3,car4,car5,car6,car7,car8,car9,car10,car11,car12,car13,car14;
    SqliteHelper sql;
    public int A1;
    String FinalAmount,carnumber,slotplace;

    SharedPreferences sharedPreferences;
    ImageView resourceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        Checkout.preload(getApplicationContext());
        sql = new SqliteHelper(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS}, 101);
        }

        car1 = (ImageView) findViewById(R.id.image1);
        car3 = (ImageView)findViewById(R.id.image3);
        car4 = (ImageView) findViewById(R.id.image4);
        car5 = (ImageView)findViewById(R.id.image5);
        car6 = (ImageView) findViewById(R.id.image6);
        car7 = (ImageView)findViewById(R.id.image7);
        car8 = (ImageView) findViewById(R.id.image8);
        car9 = (ImageView)findViewById(R.id.image9);
        car10 = (ImageView) findViewById(R.id.image10);
        car11 = (ImageView)findViewById(R.id.image11);
        car12 = (ImageView) findViewById(R.id.image12);
        car13 = (ImageView)findViewById(R.id.image13);
        car14 = (ImageView)findViewById(R.id.image14);

        car3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A2";
                setupparking(car3,slotplace);
        //        Toast.makeText(Parking.this, "A2 BOOKED" , Toast.LENGTH_LONG).show();
             //   car3.setImageResource(R.drawable.car_green);
            }
        });
        car4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A3";
                setupparking(car4,slotplace);
         //       Toast.makeText(Parking.this, "A3 BOOKED" , Toast.LENGTH_LONG).show();
               // car4.setImageResource(R.drawable.car_green);
            }
        });
        car5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A4";
                setupparking(car5,slotplace);
              //  Toast.makeText(Parking.this, "A4 BOOKED" , Toast.LENGTH_LONG).show();
            //    car5.setImageResource(R.drawable.car_green);
            }
        });
        car6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A5";
                setupparking(car6,slotplace);
            //    Toast.makeText(Parking.this, "A5 BOOKED" , Toast.LENGTH_LONG).show();
            //    car6.setImageResource(R.drawable.car_green);
            }
        });
        car7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A6";
                setupparking(car7,slotplace);
                Toast.makeText(Parking.this, "A6 BOOKED" , Toast.LENGTH_LONG).show();
            //    car7.setImageResource(R.drawable.car_green);
            }
        });
        car8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slotplace ="A7";
                setupparking(car8,slotplace);
                Toast.makeText(Parking.this, "A7 BOOKED" , Toast.LENGTH_LONG).show();
          //      car8.setImageResource(R.drawable.car_green);
            }
        });

        if (A1 == 0) {

            car1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    A1 = 1;
                    LayoutInflater myLayout = LayoutInflater.from(Parking.this);
                    final String slot = "A1";
                    View dialogView = myLayout.inflate(R.layout.text_input_car, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Parking.this);
                    alertDialogBuilder.setView(dialogView);
                    alertDialogBuilder.setTitle("ENTER CAR NUMBER");
                    alertDialogBuilder.create();

                    final EditText input = (EditText) dialogView.findViewById(R.id.input);
                    alertDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String carnumber = input.getText().toString();

                            Date currentTime = Calendar.getInstance().getTime();
                            sharedPreferences = getSharedPreferences("MyPrefs",
                                    Context.MODE_PRIVATE);
                            String email = sharedPreferences.getString("email", null);
                            String entrytime = sharedPreferences.getString("time", null);
                            String date = sharedPreferences.getString("date", null);
                            String valid = sharedPreferences.getString("validity", null);
                            String cartype = sharedPreferences.getString("type", null);
                            String endtime = sharedPreferences.getString("endtime",null);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("slot",slot);




                            if (cartype.equals("Two Wheeler")) {
                                int amount = 30 * Integer.parseInt(valid);
                                FinalAmount = String.valueOf(amount);
                                Toast.makeText(Parking.this, FinalAmount, Toast.LENGTH_LONG).show();


                            } else {
                                int amount = 40 * Integer.parseInt(valid);
                                FinalAmount = String.valueOf(amount);
                                Toast.makeText(Parking.this, FinalAmount, Toast.LENGTH_LONG).show();


                            }
                      //      sql.addcurrentbooking(email, entrytime, date, carnumber, cartype, slot, valid);
                     //       sql.allbooking(email,entrytime,endtime,date, carnumber, cartype, slot, valid,FinalAmount);
                            Toast.makeText(Parking.this, "A1 BOOKED" + currentTime, Toast.LENGTH_LONG).show();
                            car1.setImageResource(R.drawable.car_green);

//                            Intent intent = new Intent(Parking.this, checksum.class);

  //                          intent.putExtra("custid", email);
    //                        intent.putExtra("amount", FinalAmount);
      //                      startActivity(intent);
                            setuppayment(FinalAmount);


                        }
                    });
                    alertDialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });
                    alertDialogBuilder.show();
                }
            });
            //   Intent intent = new Intent(Parking.this,timeleft.class);
            //   startActivity(intent);


        } else if (A1 == 1) {
            Toast.makeText(Parking.this, "Already Booked", Toast.LENGTH_SHORT).show();

        }
    }
    private void setuppayment(String FinalAmount){
        final Activity activity = this;
        final Checkout checkout = new Checkout();
        JSONObject orderRequest = new JSONObject();
        try {

            orderRequest.put("amount", Integer.parseInt(FinalAmount)*100); // amount in the smallest currency unit
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            orderRequest.put("payment_capture", false);

            JSONObject prefill = new JSONObject();
            prefill.put("email","sehaj23rocks@gmail.com");
            prefill.put("contact","9711841198");
            orderRequest.put("prefill",prefill);
            checkout.open(activity,orderRequest);



        } catch (JsonIOException e) {
            // Handle Exception
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void setupparking(final ImageView resourceName, final String slot){
        LayoutInflater myLayout = LayoutInflater.from(Parking.this);
        //final String slot = "A1";
        View dialogView = myLayout.inflate(R.layout.text_input_car, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Parking.this);
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setTitle("ENTER CAR NUMBER");
        alertDialogBuilder.create();
        final EditText input = (EditText) dialogView.findViewById(R.id.input);
        alertDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 carnumber = input.getText().toString();

                Date currentTime = Calendar.getInstance().getTime();
                sharedPreferences = getSharedPreferences("MyPrefs",
                        Context.MODE_PRIVATE);
                String email = sharedPreferences.getString("email", null);
                String entrytime = sharedPreferences.getString("time", null);
                String date = sharedPreferences.getString("date", null);
                String valid = sharedPreferences.getString("validity", null);
                String cartype = sharedPreferences.getString("type", null);
                String endtime = sharedPreferences.getString("endtime",null);




                if (cartype.equals("Two Wheeler")) {
                    int amount = 30 * Integer.parseInt(valid);
                    FinalAmount = String.valueOf(amount);
                    Toast.makeText(Parking.this, FinalAmount, Toast.LENGTH_LONG).show();


                } else {
                    int amount = 40 * Integer.parseInt(valid);
                    FinalAmount = String.valueOf(amount);

//
            }
////                sql.addcurrentbooking(email, entrytime, date, carnumber, cartype, slotplace, valid);
//                sql.allbooking(email,entrytime,endtime,date, carnumber, cartype, slotplace, valid,FinalAmount);

//                int resID = getResources().getIdentifier(String.valueOf(resourceName), "id", getPackageName());
//                ImageView im = (ImageView) findViewById(resID);
//                im.setImageResource(R.drawable.car_green);
                resourceName.setImageResource(R.drawable.car_green);
                Toast.makeText(Parking.this, slot+"BOOKED" , Toast.LENGTH_LONG).show();



//                            Intent intent = new Intent(Parking.this, checksum.class);

                //                          intent.putExtra("custid", email);
                //                        intent.putExtra("amount", FinalAmount);
                //                      startActivity(intent);
                setuppayment(FinalAmount);


            }
        });

        alertDialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }

        });
        alertDialogBuilder.show();

        //   Intent intent = new Intent(Parking.this,timeleft.class);
        //   startActivity(intent);


        }



    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Payment Successfull",Toast.LENGTH_LONG).show();
        sharedPreferences = getSharedPreferences("MyPrefs",
                Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String entrytime = sharedPreferences.getString("time", null);
        String date = sharedPreferences.getString("date", null);
        String valid = sharedPreferences.getString("validity", null);
        String cartype = sharedPreferences.getString("type", null);
        String endtime = sharedPreferences.getString("endtime",null);

        if (cartype.equals("Two Wheeler")) {
            int amount = 30 * Integer.parseInt(valid);
            FinalAmount = String.valueOf(amount);
          //  Toast.makeText(Parking.this, FinalAmount, Toast.LENGTH_LONG).show();


        } else {
            int amount = 40 * Integer.parseInt(valid);
            FinalAmount = String.valueOf(amount);
       //     Toast.makeText(Parking.this, FinalAmount, Toast.LENGTH_LONG).show();


        }
        sql.addcurrentbooking(email, entrytime, date, carnumber, cartype, slotplace, valid);
        sql.allbooking(email,entrytime,endtime,date, carnumber, cartype, slotplace, valid,FinalAmount);

        Intent intent=new Intent(Parking.this,timer.class);
        startActivity(intent);



    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Payment Not Successfull",Toast.LENGTH_LONG).show();


    }

//    @Override
//    public void onBackPressed() {
//        Intent intent = new Intent(this,HomeActivity.class);
//        startActivity(intent);
//        super.onBackPressed();
//
//    }
}
