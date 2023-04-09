/**
 * Sources:
 * https://github.com/yuriy-budiyev/code-scanner
 * https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
 */
package com.example.ramq;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class QRScannerActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE = 100;
    private CodeScanner mCodeScanner;
    private TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);


        SharedPreferences tripInfo = getSharedPreferences("TripInformation",MODE_PRIVATE);
        String expectedQr = tripInfo.getString("qrCodeGenerated","Hello!");


        checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);


        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        output = findViewById(R.id.text_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) { //when qr code is decoded
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
//                        output.setText(result.getText());
                        if(expectedQr.equals(result.getText())){
                            output.setText("QR Code Scanned Successfully");
                            Intent intent = new Intent(QRScannerActivity.this,InRideActivity.class);
                            startActivity(intent);
                        }else{
                            output.setText("QR Code not recognized, tap the camera screen to try again");
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("");
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }


    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(QRScannerActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(QRScannerActivity.this, new String[] { permission }, requestCode);
        }
        else {
//            Toast.makeText(QRScannerActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }

    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(QRScannerActivity.this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QRScannerActivity.this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

}