package com.example.karan.bikerent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText names, email, password, phone;
    Button Signup;
    private TextView signInLink;
    private ProgressDialog loadingBar;


    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        names = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);
        Signup = (Button) findViewById(R.id.btnRegister);
        signInLink = (TextView)findViewById(R.id.link_to_login);

        Signup.setOnClickListener(this);
        loadingBar = new ProgressDialog(this);

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(SignUpActivity.this, activity_SignIn.class);
                startActivity(signInIntent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!= null){
            //handle the already login user

        }
    }

    private void registerUser() {

        //getting email and password from edit texts
        final String Name = names.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        final String Phone = phone.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            Toast.makeText(getApplicationContext(), "Please enter valid email!", Toast.LENGTH_SHORT).show();
            return;

        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if (Password.length()<6){
            Toast.makeText(this,"Please enter password more than 6 digit",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(Phone)) {
            Toast.makeText(this, "Please enter phone", Toast.LENGTH_LONG).show();
            return;
        }
        if (Phone.length()!= 10){
            Toast.makeText(this,"Please enter valid mobile number",Toast.LENGTH_LONG).show();
            return;
        }


        //if the email, password or phone are not empty
        //displaying a progress dialog

        loadingBar.setTitle("Creating Your Account");
        loadingBar.setMessage("Please wait, while we are checking the credentials.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();


        firebaseAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success

                        if(task.isSuccessful()){
                            Users users = new Users(
                                    Name,
                                    Email,
                                    Phone
                            );
                            FirebaseDatabase.getInstance().getReference().
                                    child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                    }
                                    else {
                                        Toast.makeText(SignUpActivity.this,"Registration not complete ",Toast.LENGTH_LONG).show();
                                        loadingBar.dismiss();
                                        //display a failure message
                                    }


                                }

                            });

                            //display some message here
                        }
                        else{
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                            loadingBar.dismiss();
                        }


                    }
                });

    }
    @Override
    public void onClick(View view) {

        registerUser();
    }




}

