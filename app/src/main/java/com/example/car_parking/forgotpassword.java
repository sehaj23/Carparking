package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {
    Button forgotpassword;
    EditText emaill;
    SqliteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgotpassword = (Button)findViewById(R.id.submitforgot);
        emaill = (EditText)findViewById(R.id.email);


        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String checkemail =    emaill.getText().toString();

               if( sql.isEmailExists(checkemail)){
                   Toast.makeText(getApplicationContext(),"Your Password Has been Mailed to You",Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(forgotpassword.this,Login.class);
                   startActivity(intent);

               }else{
                   Toast.makeText(getApplicationContext(),"Your Password Has been Mailed to You",Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(forgotpassword.this,Register.class);
                   startActivity(intent);
               }



            }
        });
    }
}
