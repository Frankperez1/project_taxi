package com.example.uberclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uberclone.R;

public class MainActivity extends AppCompatActivity {
    Button mButtonCliente;
    Button mButtonConductor;
    SharedPreferences mpref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpref=getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);
        SharedPreferences.Editor editor=mpref.edit();

        mButtonCliente = findViewById(R.id.btnIamcliente);
        mButtonConductor = findViewById(R.id.btnIamconductor);

        mButtonConductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user","Driver");
                editor.apply();
                // Cuando se hace clic en el botón de conductor, iniciar selectoptionActivity
                Intent intent = new Intent(MainActivity.this, selectoptionActivity.class);
                startActivity(intent);
            }
        });
        mButtonCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user","Client");
                editor.apply();
                // Cuando se hace clic en el botón de cliente, iniciar selectoptionActivity
                Intent intent = new Intent(MainActivity.this, selectoptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
