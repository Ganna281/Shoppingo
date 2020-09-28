package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.spark.submitbutton.SubmitButton;

import java.util.jar.Attributes;

public class SignInActivity extends AppCompatActivity {
    MaterialRippleLayout  ForgetPassword;
    SubmitButton button;
    SignInButton signInGmail;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
     static EditText Mail , Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        button = findViewById(R.id.Login);
        ForgetPassword = findViewById(R.id.ForgetPassword);
        Password = findViewById(R.id.password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");


        signInGmail = findViewById(R.id.gmail);
        signInGmail.setSize(SignInButton.SIZE_STANDARD);
        Mail = findViewById(R.id.mail);
        signInGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.gmail:
                        signIn();
                        break;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String mail = Mail.getText().toString();
                        String  password = Password.getText().toString();
                        Shoppingo db = new Shoppingo(SignInActivity.this);
                        if (mail.equals("")||password.equals(""))
                        {
                            Toast.makeText(SignInActivity.this,"Please Complete sign in data",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (db.checkAccountInfo(mail, password)) {
                                Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                Intent startActivity = new Intent(getApplicationContext(), Menu.class);
                                startActivity(startActivity);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignInActivity.this,"E-mail or Password is in correct",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, 3050);

            }
        });
        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startActivity = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
                startActivity(startActivity);
                sendMail();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void sendMail() {

        String mail = Mail.getText().toString().trim();
        String message = "Your Activation Code is 1234.";
        String subject = "Forgotten Password";

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }
    @Override
    public void onStart(){
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Mail.setText(account.getEmail());
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
            //updateUI(null);
        }
    }
}