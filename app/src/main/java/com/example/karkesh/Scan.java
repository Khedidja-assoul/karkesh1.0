package com.example.karkesh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scan extends AppCompatActivity implements View.OnClickListener {

    DB_Manager maDataBaseManager;
    ImageView scanButton;
    Button reset;
    ImageView reaction;
    int i = 0;
    String code1;
    String code2;
    ImageView qr1;
    ImageView qr2;
    ImageView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        maDataBaseManager = new DB_Manager(getApplicationContext());
        qr1 = findViewById(R.id.qr1);
        qr2 = findViewById(R.id.qr2);
        reaction = findViewById(R.id.reaction);
        text = findViewById(R.id.text);

        scanButton = findViewById(R.id.scan);
        reset = findViewById(R.id.reset);

        scanButton.setOnClickListener(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qr1.setImageResource(R.drawable.qrcodegray);
                qr2.setImageResource(R.drawable.qrcodegray);

                code1 = "";
                code2 = "";
                i = 0;
                scanButton.setVisibility(View.VISIBLE);
                reaction.setImageBitmap(null);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                if(i==0) {
                    code1 = intentResult.getContents();
                    qr1.setImageResource(R.drawable.qrcode);
                    text.setImageResource(R.drawable.deuxiemetext);
                }
                else if (i==1) {
                    text.setImageResource(R.drawable.deuxiemetext);
                    code2 = intentResult.getContents();
                    qr2.setImageResource(R.drawable.qrcode);
                    boolean valid = maDataBaseManager.existCodes(code1, code2);²
                    if (valid) {
                        reaction.setImageResource(R.drawable.emojii);
                    } else {
                        reaction.setImageResource(R.drawable.sadd);
                    }
                    scanButton.setVisibility(View.INVISIBLE);
                }
                i++;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }
}
