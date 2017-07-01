package com.example.vimadhavan.assignment92;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int RequestPermissionCode = 1;
    private Button button;
    private ImageView imageview;
    private Bitmap bitmap;
    private View view;
    private ByteArrayOutputStream bytearrayoutputstream;
    private File file;
    private FileOutputStream fileoutputstream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnableRuntimePermission();
        button = (Button)findViewById(R.id.saveBtn);
        imageview = (ImageView)findViewById(R.id.imageView);
        bytearrayoutputstream = new ByteArrayOutputStream();

        button.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {


                Drawable drawable = getResources().getDrawable(R.drawable.img);

                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

                bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

                file = new File( Environment.getExternalStorageDirectory() + "/img_saved.png");
                try {
                    Log.e("path", "path= "+ new File( Environment.getExternalStorageDirectory() + "/img_saved.png").getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try

                {
                    file.createNewFile();

                    fileoutputstream = new FileOutputStream(file);

                    fileoutputstream.write(bytearrayoutputstream.toByteArray());

                    fileoutputstream.close();

                }

                catch (Exception e)

                {

                    e.printStackTrace();

                }

                Toast.makeText(MainActivity.this, "Image Saved Successfully", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Toast.makeText(MainActivity.this, "WRITE EXTERNAL STORAGE permission allows us to Access to write this image", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

        }

    }
}
