package com.example.aula4app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bContatos, bWeb, bCall, bMaps1, bMaps2, bMaps3, bPicIti;
    TextView tvNome, tvTelefone;
    ImageView ivImagem;

    int contato = 1, imagem = 2;

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

        tvNome = findViewById(R.id.tvNome);
        tvTelefone = findViewById(R.id.tvTelefone);

        ivImagem = findViewById(R.id.ivImagem);

        bContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.android.contacts/contacts/");
                Intent it = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(it, contato);
            }
        });

        bWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com.br");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel: 99887766");
                Intent it = new Intent(Intent.ACTION_CALL, uri);
                startActivity(it);
            }
        });

        bMaps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:0,0?q=Sete+de+Setembro,Curitiba");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        bMaps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("geo:-25.443195,-49.280977");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        bMaps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inicio = "-25.443195, -49.280977";
                String fim = "-25.442207, -49.278403";
                Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr="+inicio+"&daddr="+fim+"&hl=pt");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
            }
        });

        bPicIti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, imagem);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){

            if (requestCode == contato) {
                Uri uri = data.getData();

                Cursor c = getContentResolver().query(uri, null, null, null, null);
                c.moveToNext();

                int nameCol = c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
                int idCol = c.getColumnIndexOrThrow(ContactsContract.Contacts._ID);

                String nome = c.getString(nameCol);
                String id = c.getString(idCol);

                c.close();

                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                phones.moveToNext();

                String phoneNumber = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));

                phones.close();

                tvNome.setText(nome);
                tvTelefone.setText(phoneNumber);
            } else if (requestCode == imagem){
                Uri uri = data.getData();

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                ivImagem.setImageBitmap(imageBitmap);
            }

        }


    }
}