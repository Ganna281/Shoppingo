package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.spark.submitbutton.SubmitButton;

public class MainActivity extends AppCompatActivity {

    public int user;
    MaterialRippleLayout Register_Ripple, SignIn_Ripple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Register_Ripple = findViewById(R.id.Register);
        SignIn_Ripple = findViewById(R.id.SignIn);

        Register_Ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        SignIn_Ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

       Log.d("Salma", "onClick: "+R.drawable.cloth1);
    }

    public int getUser()
    {
        return user;
    }

}