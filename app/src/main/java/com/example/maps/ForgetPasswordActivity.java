package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.spark.submitbutton.SubmitButton;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText Password1, Password2;
    MaterialRippleLayout Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        Password1 = findViewById(R.id.newPassword);
        Password2 = findViewById(R.id.newPassword2);
        Login = findViewById(R.id.log);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Password1.getText().toString().equals(Password2.getText().toString())) {
                    Intent startActivity = new Intent(getApplicationContext(), Menu.class);
                    startActivity(startActivity);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}