package com.example.maps;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.spark.submitbutton.SubmitButton;

public class RegisterActivity extends AppCompatActivity{
    SubmitButton button;
    SignInButton signInGmail;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    EditText Name, Mail, Password, Confirm;
    Shoppingo db = new Shoppingo(RegisterActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = findViewById(R.id.Login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");

        Name =findViewById(R.id.name);
        Mail = findViewById(R.id.mail);
        Password = findViewById(R.id.password);
        Confirm = findViewById(R.id.ConfirmPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db.checkEmail(Mail.getText().toString())) {
                    if (Password.getText().toString().equals(Confirm.getText().toString())) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                db.AddUser(Name.getText().toString(), Mail.getText().toString(), Password.getText().toString(), "31-8");
                                Intent startActivity = new Intent(getApplicationContext(), Menu.class);
                                startActivity(startActivity);
                                finish();
                            }
                        }, 3050);
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You Have already an account", Toast.LENGTH_LONG).show();
                }
            }

        });
        signInGmail = findViewById(R.id.gmail);
        signInGmail.setSize(SignInButton.SIZE_STANDARD);
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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
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
            Name.setText(account.getDisplayName());
            Mail.setText(account.getEmail());
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signIn failed" + e.getStatusCode());
            //updateUI(null);
        }
    }
}
