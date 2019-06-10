package com.example.footsteps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private TextView loginheading;
    private TextView signup;//this is declare for signup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //assigning id's to variable
        email=(EditText)findViewById(R.id.login_email);
        password=(EditText)findViewById(R.id.login_password);
        login=(Button) findViewById(R.id.login_button);
        loginheading=(TextView)findViewById(R.id.login_heading);
        signup=(TextView)findViewById(R.id.login_signup);//signup textview

        //action happening after clicking login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString(),password.getText().toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this is a new user
                //so we need to sign up him to sign up page

                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
    }

    private void validate(String useremail,String userpassword )
    {
        if((useremail=="admin") && (userpassword=="1234"))
        {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
    }
}
