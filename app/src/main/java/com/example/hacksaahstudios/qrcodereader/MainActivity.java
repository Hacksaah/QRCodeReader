package com.example.hacksaahstudios.qrcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openScanner(View view)
    {
        Intent openScannerActivity = new Intent(this, ScannerActivity.class);
        startActivity(openScannerActivity);
    }
}
