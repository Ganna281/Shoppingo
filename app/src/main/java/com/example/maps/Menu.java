package com.example.maps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    MaterialRippleLayout Search, Voice, Camera;
    final int VOICE_REQUEST = 1999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        getSupportActionBar().setTitle(" ");

        ArrayList<Category> categories = new ArrayList<>();

        // Read Categories

        Shoppingo db = new Shoppingo(Menu.this);
        Cursor cursor = db.getCategories();

        while (!cursor.isAfterLast()) {
            categories.add(new Category(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            cursor.moveToNext();
        }

        MenuRecyclerViewAdapter recyclerViewAdapter = new MenuRecyclerViewAdapter(Menu.this, categories);

        RecyclerView recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Search = findViewById(R.id.search);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText searchText = findViewById(R.id.searchText);
                String text = searchText.getText().toString();
                Shoppingo DB = new Shoppingo(Menu.this);
                Cursor cursor = DB.getAll(text, new String[] {"itemID","itemname", "itembrand", "itemprice", "itemimage", "itemmaterial", "itemcolor", "itemmodel","numberOfPices","categoryID"});
                ArrayList<Item> items = new ArrayList<>();
                while (!cursor.isAfterLast())
                {
                    items.add(new Item(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
                    cursor.moveToNext();
                }
                Toast.makeText (Menu.this,String.valueOf(items.size()),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent (Menu.this,ViewProducts.class);
                intent.putExtra("MyClass", (Serializable) items);
                intent.putExtra("category", (Serializable) "Search Result");
                startActivity(intent);

            }
        });

        Voice = findViewById(R.id.voice);
        Voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                startActivityForResult(intent, VOICE_REQUEST);
            }
        });
        Camera = findViewById(R.id.camera);
        Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText (Menu.this,"HERE!!!",Toast.LENGTH_SHORT).show();
        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null) {


                    Shoppingo DB = new Shoppingo(Menu.this);
                    Cursor cursor = DB.getAll(result.get(0), new String[] {"itemID","itemname", "itembrand", "itemprice", "itemimage", "itemmaterial", "itemcolor", "itemmodel","numberOfPices","categoryID"});
                    ArrayList<Item> items = new ArrayList<>();
                    while (!cursor.isAfterLast())
                    {
                        items.add(new Item(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getInt(8),cursor.getInt(9)));
                        cursor.moveToNext();
                    }
                    Toast.makeText (Menu.this,String.valueOf(items.size()),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent (Menu.this,ViewProducts.class);
                    intent.putExtra("MyClass", (Serializable) items);
                    intent.putExtra("category", (Serializable) "Search Result");
                    startActivity(intent);

                }
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