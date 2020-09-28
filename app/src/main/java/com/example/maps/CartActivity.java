package com.example.maps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.spark.submitbutton.SubmitButton;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    MaterialRippleLayout Account;
    SubmitButton Order;
    ImageView imageView;
    Shoppingo DB;
    ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");

        Account = findViewById(R.id.account);
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
               startActivity(intent);
            }
        });

        DB = new Shoppingo(CartActivity.this);
        storeDataInArrays();
     /*   cartItems.add(new CartItem("Accessorie",10000,2131165278));
        cartItems.add(new CartItem("Accessorie",5000,2131165279));*/
        CartAdapter cartAdapter = new CartAdapter(CartActivity.this,cartItems);

        RecyclerView recyclerView = findViewById(R.id.recyclerCart);
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Order = findViewById(R.id.Order);
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                        startActivity(intent);
                    }
                }, 3050);
            }
        });
    }
   void storeDataInArrays(){
        String[] values = { "product_image", "product_name", "product_price"};
        Cursor cursor = DB.readCartData(values,ProfileActivity.user.ID);
        if(cursor.getCount() == 0){

            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }else{
            while (!cursor.isAfterLast()){
                cartItems.add(new CartItem(cursor.getString(1),cursor.getInt(2),cursor.getInt(0)));
               cursor.moveToNext();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}