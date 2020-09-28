package com.example.maps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewProducts extends AppCompatActivity {
	 MaterialRippleLayout Account, Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");

        TextView type = findViewById(R.id.type);
        String category = (String) getIntent().getSerializableExtra("category");

        type.setText(category);

        ArrayList<Item> items= (ArrayList<Item>) getIntent().getSerializableExtra("MyClass");


        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(ViewProducts.this, items);

        RecyclerView recyclerView = findViewById(R.id.productRecyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(recyclerViewAdapter);



        Account = findViewById(R.id.account);
        Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        Cart = findViewById(R.id.cart);
        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

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