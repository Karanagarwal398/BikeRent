package com.example.karan.bikerent;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.karan.bikerent.models.UserObject;
import com.example.karan.bikerent.network.GsonRequest;
import com.example.karan.bikerent.utils.Constants;
import com.example.karan.bikerent.utils.CustomApplication;
import com.example.karan.bikerent.utils.Helper;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.HashMap;
import java.util.Map;

public class activity_SignIn extends AppCompatActivity {
    private static final String TAG = activity_SignIn.class.getSimpleName();

    private EditText email, password;

    private TextView forgottenPassword, signInLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_in);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        forgottenPassword = (TextView)findViewById(R.id.forgotten_password);
        signInLink = (TextView)findViewById(R.id.link_to_registration);

        forgottenPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgottenIntent = new Intent(activity_SignIn.this, ForgottenActivity.class);
                startActivity(forgottenIntent);
            }
        });

        signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = new Intent(activity_SignIn.this, SignUpActivity.class);
                startActivity(signInIntent);
            }
        });

        Button loginButton = (Button)findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EmailValidator validator = EmailValidator.getInstance();

                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                /*if(TextUtils.isEmpty(mEmail) || TextUtils.isEmpty(mPassword)){
                    Helper.displayErrorMessage(SignInActivity.this, "Email and password fields must be filled");
                }else if(!validator.isValid(mEmail)){
                        Helper.displayErrorMessage(LoginActivity.this, "You have entered an invalid email");
                }else{
                    if(Helper.isNetworkAvailable(SignInActivity.this)){
                        loginCallToServer(mEmail, mPassword);
                    }else{
                        Helper.displayErrorMessage(SignInActivity.this, "No network available");
                    }
                }*/

                Intent homeIntent = new Intent(activity_SignIn.this, HomeActivity.class);
                startActivity(homeIntent);
            }
        });
    }

    private void loginCallToServer(String email, String password){
        Map params = getParams(email, password);
        GsonRequest<UserObject> serverRequest = new GsonRequest<UserObject>(
                Request.Method.POST,
                Constants.PATH_TO_SERVER_LOGIN,
                UserObject.class,
                params,
                createRequestSuccessListener(),
                createRequestErrorListener());

        ((CustomApplication)getApplication()).getNetworkCall().callToRemoteServer(serverRequest);
    }

    private Map getParams(String email, String password){
        Map<String, String> params = new HashMap<String,String>();
        params.put(Constants.EMAIL, email);
        params.put(Constants.PASSWORD, password);
        return params;
    }

    private Response.Listener<UserObject> createRequestSuccessListener() {
        return new Response.Listener<UserObject>() {
            @Override
            public void onResponse(UserObject response) {
                try {
                    if(response != null){
                        //save user login data to a shared preference
                        String userData = ((CustomApplication)getApplication()).getGsonObject().toJson(response);
                        ((CustomApplication)getApplication()).getShared().setUserData(userData);

                        Intent homeIntent = new Intent(activity_SignIn.this, HomeActivity.class);
                        startActivity(homeIntent);
                    } else{
                        Helper.displayErrorMessage(activity_SignIn.this, "Login failed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener createRequestErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

}
