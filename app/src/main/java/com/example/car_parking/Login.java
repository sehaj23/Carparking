package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText email,password;
    Button login,signup;
    SqliteHelper sql;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sql = new SqliteHelper(this);
        final String MyPREFERENCES = "MyPrefs" ;

        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    //Get values from EditText fields
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();


                    //Authenticate user
                    User currentUser = sql.Authenticate(new User(null, null, Email, Password));


                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email",Email);
                        editor.putString("password",Password);
                        editor.commit();

                        Toast.makeText(Login.this,"Successfully logged in",Toast.LENGTH_LONG).show();
                    //    Intent intent = new Intent(Login.this,home_Activity.class);
                      //  startActivity(intent);
                        //User Logged in Successfully Launch You home screen activity
                       Intent intent=new Intent(Login.this,HomeActivity.class);
                        startActivity(intent);
                    } else {

                        //User Logged in Failed

                        Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;

            Toast.makeText(Login.this,"Enter Valid Email",Toast.LENGTH_LONG).show();
            email.setError("Enter  Valid Email");
            email.requestFocus();
        } else {
            valid = true;

          //  Toast.makeText(Login.this,"enter valid email",Toast.LENGTH_LONG).show();
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;

            Toast.makeText(Login.this,"Enter Valid Password",Toast.LENGTH_LONG).show();
        } else {
            if (Password.length() > 5) {
                valid = true;

            //    Toast.makeText(Login.this,"Enter Valid Password",Toast.LENGTH_LONG).show();
            } else {
                valid = false;

                Toast.makeText(Login.this,"Enter Valid Password",Toast.LENGTH_LONG).show();
            }
        }

        return valid;
    }
    }

