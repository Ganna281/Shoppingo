package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class VoiceActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener  {
    private final int VOICE_REQUEST = 1999;
    TextView speechText;
    int selectedCategoryID;
    String productDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        speechText = (TextView) findViewById(R.id.speech_text);
        Button speechToText = (Button) findViewById(R.id.speech_to_text);

        Shoppingo shoppingo = new Shoppingo(VoiceActivity.this);
        Cursor categoriesCursor = shoppingo.getCategories();
        ArrayList<String> categories = new ArrayList<>();

        while (!categoriesCursor.isAfterLast())
        {
            categories.add(categoriesCursor.getString(1));
            categoriesCursor.moveToNext();

        }

        Spinner category = (Spinner) findViewById(R.id.category);
        category.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(aa);

        speechToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.speech_to_text) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    startActivityForResult(intent, VOICE_REQUEST);
                }
            }
        });



    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        RadioButton nameButton = findViewById(R.id.nameRadioButton);
        RadioButton brandButton = findViewById(R.id.brandRadioButton);

        if (requestCode == VOICE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                ArrayList<Item> items = new ArrayList<>();
                if (result != null) {
                   //speechText.setText(result.get(0));

                    if (nameButton.isChecked())
                    {
                        productDetail = "itemname";
                    }
                    else if (brandButton.isChecked())
                    {
                        productDetail = "itembrand";
                    }
                    else
                    {
                        Toast.makeText(VoiceActivity.this,"Please Select the Product Details",Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(VoiceActivity.this, ViewProducts.class);
                    intent.putExtra("MyClass", (Serializable) items);
                    intent.putExtra("Name","Search Result");
                    startActivity(intent);
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCategoryID = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}