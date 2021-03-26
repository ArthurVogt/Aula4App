package com.example.aula4app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bContatos, bWeb, bCall, bMaps1, bMaps2, bMaps3, bPicIti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bContatos = findViewById(R.id.bContatos);
        bWeb = findViewById(R.id.bWeb);
        bCall = findViewById(R.id.bCall);
        bMaps1 = findViewById(R.id.bMaps1);
        bMaps2 = findViewById(R.id.bMaps2);
        bMaps3 = findViewById(R.id.bMaps3);
        bPicIti = findViewById(R.id.bPicIti);

        bWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com.br");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
            }
        });
    }
}