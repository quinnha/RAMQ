package com.example.ramq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScanQRCodeButtonActivity extends AppCompatActivity {
    private Button scanQRCodeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode_button);
        scanQRCodeButton = findViewById(R.id.scanQRCodeButton);

        scanQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanQRCodeButtonActivity.this, QRScannerActivity.class);
                startActivity(intent);
            }
        });

    }
}