package com.example.car_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name,email,password;
    Button register,login;
    SqliteHelper sql;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sql = new SqliteHelper(this);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String username = name.getText().toString();
                    String useremail = email.getText().toString();
                    String userpassword = password.getText().toString();
                    if (!sql.isEmailExists(useremail)) {

                        sql.adduser(new User(null, username, useremail, userpassword));

                        Toast.makeText(Register.this, "User Created", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    };
                }

            }
        });




    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = name.getText().toString();
        String Email =email.getText().toString();
        String Password = password.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (UserName.length() >= 5) {
                valid = true;
               // Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();
            } else {
                valid = false;
                Toast.makeText(Register.this,"Username Length Should be 5 ",Toast.LENGTH_LONG).show();
                return  false;
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            Toast.makeText(Register.this,"Not A valid Email Address",Toast.LENGTH_LONG).show();
            return  false;
        } else {
            valid = true;
           // Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();

            if (Password.length() > 5) {
                valid = true;
             //   Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();
            } else {
                valid = false;
                Toast.makeText(Register.this,"error",Toast.LENGTH_LONG).show();
                return false;
            }
        }


        return valid;
    }

}



