package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {
    private final int camera_requset = 1888;
    private ImageView myCapturedImage;
    Button open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        open = (Button) findViewById(R.id.open_camera);
        myCapturedImage = (ImageView) findViewById(R.id.capture_image);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Cameraintent, camera_requset);
            }
        });
    }

    public void onActivityResult(int request_code, int result_code, Intent data) {
        super.onActivityResult(request_code, result_code, data);
        if (request_code == camera_requset && result_code == RESULT_OK) {
            Bitmap Myimage = (Bitmap) data.getExtras().get("data");
            myCapturedImage.setImageBitmap(Myimage);

        }
    }
}