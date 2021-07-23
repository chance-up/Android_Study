package org.techtown.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
    }

    public void onBtnHClicked(View v){
        Intent myIntent = new Intent(getApplicationContext(),LinearHActivity.class);
        startActivity(myIntent);
    }
    public void onBtnVClicked(View v){
        Intent myIntent = new Intent(getApplicationContext(),LinearVActivity.class);
        startActivity(myIntent);
    }

    public void onBtnLinearInClicked(View v){
        Intent myIntent = new Intent(getApplicationContext(),LinearInActivity.class);
        startActivity(myIntent);
    }

}