package com.example.uberclone.activity.driver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.uberclone.R;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.uberclone.R;
import com.example.uberclone.models.Client;
import com.example.uberclone.providers.AuthProviders;
import com.example.uberclone.providers.ClientProvider;
import com.example.uberclone.providers.DriverProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.uberclone.models.Driver;

public class RegisterDriverActivity extends AppCompatActivity {

    AuthProviders mAuthProviders;
    DriverProvider mDriverProvider;
    DatabaseReference mDatabase;

    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputVehiculeBrand;
    TextInputEditText mTextInputVehiculePlaca;
    AlertDialog mDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter_driver);

        mAuthProviders = new AuthProviders();
        mDriverProvider = new DriverProvider();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        mButtonRegister = findViewById(R.id.buttonRegister);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mTextInputVehiculeBrand=findViewById(R.id.textInputVehiculeBrand);
        mTextInputVehiculePlaca=findViewById(R.id.textInputVehiclePlaca);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRegister();
            }
        });
    }

    void clickRegister() {
        String name = mTextInputName.getText().toString();
        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();
        String vehiculeBrand = mTextInputVehiculeBrand.getText().toString();
        String vehiculePlate = mTextInputVehiculePlaca.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !vehiculeBrand.isEmpty() && !vehiculePlate.isEmpty()) {
            if (password.length() >= 6) {
                register(name, email, password,vehiculePlate,vehiculeBrand);
            } else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void register(final String name, String email, String password, String vehiculeBand, String vehiculePlate) {
        mAuthProviders.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Driver driver = new Driver(id, name,email,vehiculeBand,vehiculePlate);
                    create(driver);
                } else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo registrar el usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(Driver driver) {
        mDriverProvider.create(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent=new Intent(RegisterDriverActivity.this,MapDriverActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                } else {
                    Toast.makeText(RegisterDriverActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}