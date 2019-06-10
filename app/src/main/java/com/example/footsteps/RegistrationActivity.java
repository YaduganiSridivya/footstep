package com.example.footsteps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText username,userpassword,useremail;
    private Button regbutton;
    private TextView usersignup,userlogin;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        //declare firebase object
        firebaseAuth=FirebaseAuth.getInstance();


        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    //upload to database
                    String user_email=useremail.getText().toString().trim();
                    String user_password=userpassword.getText().toString().trim();

                    /*firebaseAuth.createUserWithEmailAndPassword(user_email,user_password);  //this statement is sufficient to add to database
                    * but we are using addoncompletion method to intimate that task completed*/


                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                //on successful completion of registration we move to login page

                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                        }
                    });

                }
            }


        });

        userlogin.setOnClickListener(new View.OnClickListener()
        {
         @Override
            public void onClick(View view)
         {
             Intent intent1=new Intent(RegistrationActivity.this,MainActivity.class);
         }
        });
    }

    private void setupUIViews()
    {
        username=(EditText)findViewById(R.id.signup_name);
        userpassword=(EditText)findViewById(R.id.signup_password);
        useremail=(EditText)findViewById(R.id.signup_email);
        regbutton=(Button) findViewById(R.id.signup_button);
        usersignup=(TextView)findViewById(R.id.signup_textview);
        userlogin=(TextView)findViewById(R.id.signup_loginin);

    }
    private Boolean validate()
    {
        Boolean result=false;

        String name=username.getText().toString();
        String password=userpassword.getText().toString();
        String email=useremail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }

        return result;
    }
}
