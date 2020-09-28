package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;

public class ProductDetails extends AppCompatActivity {
	MaterialRippleLayout Add, Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Item item ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");

        item =(Item)  getIntent().getSerializableExtra("MyClass");
        TextView textview1 = findViewById(R.id.textview1);
        TextView textview2 = findViewById(R.id.textview2);
        TextView textview3 = findViewById(R.id.textview3);
        TextView textview4 = findViewById(R.id.textview4);
        TextView name = findViewById(R.id.Name);
        final ImageView image = findViewById(R.id.imageView);


            name.setText(item.name+"-"+ item.brand);
            textview1.setText("-"+item.color);
            textview2.setText("-Model"+ item.model);
            textview3.setText("-Price"+Double.toString(item.price)+"$");
            image.setImageResource(item.imagedrawable);


        Add = findViewById(R.id.add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Shoppingo db = new Shoppingo(ProductDetails.this);
                if (item.quantity>0) {
                    db.AddCart(item.name, item.price, item.imagedrawable, ProfileActivity.user.ID, 1);
                    User.TotalPrice += item.price;
                    item.quantity--;
                    //update number of pices
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Sold Out!!",Toast.LENGTH_SHORT);
                }

             //   Intent intent = new Intent(getApplicationContext(), CartActivity.class);
              //  startActivity(intent);
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