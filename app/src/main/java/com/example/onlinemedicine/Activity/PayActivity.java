package com.example.onlinemedicine.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.onlinemedicine.Activity.MainActivity;
import com.example.onlinemedicine.R;

public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
    }

    public void go_to_back(View view) {
        Intent back=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        finish();
    }
}