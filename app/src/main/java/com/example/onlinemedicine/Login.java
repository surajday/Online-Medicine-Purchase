package com.example.onlinemedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void gotologin(View view) {
        Intent mySuperIntent = new Intent(Login.this,MainActivity.class);
        startActivity(mySuperIntent);
        finish();
    }
}