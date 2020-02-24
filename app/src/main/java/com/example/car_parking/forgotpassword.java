package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {
    Button forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpassword = (Button)findViewById(R.id.submitforgot);

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Your Password Has been Mailed to You",Toast.LENGTH_LONG).show();
            }
        });
    }
}
