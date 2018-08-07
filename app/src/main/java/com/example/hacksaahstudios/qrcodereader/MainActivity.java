package com.example.hacksaahstudios.qrcodereader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler
{
    private static final int CAMERA_REQUEST_CODE = 0;

    private ZXingScannerView zXingScannerView;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);

        askPermissions(Manifest.permission.CAMERA, CAMERA_REQUEST_CODE);

        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override public void handleResult(Result result)
    {
        Intent openWebView = new Intent(this, WebViewActivity.class);
        openWebView.putExtra("url", result.getText());
        startActivity(openWebView);
    }

    @Override protected void onPause()
    {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override protected void onResume()
    {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    private void askPermissions(String permission, int requestCode)
    {
        if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {permission}, requestCode);
    }
}
