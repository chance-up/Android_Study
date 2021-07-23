package org.techtown.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn1Clicked(View v){
        Toast.makeText(this,"토스트 메시지 Test",Toast.LENGTH_LONG).show();
    }
    public void onBtn2Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(myIntent);
    }
    public void onBtn3Clicked(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
        startActivity(myIntent);
    }

    public void onBtnLinearClicked(View V){
        Intent myIntent = new Intent(getApplicationContext(),LinearLayoutActivity.class);
        startActivity(myIntent);
    }

    public void onBtnConstraintClicked(View V){
        Intent myIntent = new Intent(getApplicationContext(),ConstraintLayoutActivity.class);
        startActivity(myIntent);
    }

    public void onBtnJavacodeLayoutClicked(View V){
        Intent myIntent = new Intent(getApplicationContext(),JavaCodeLayout.class);
        startActivity(myIntent);
    }

    public void onBtnScrollClicked(View V){
        Intent myIntent = new Intent(getApplicationContext(),ScrollActivity.class);
        startActivity(myIntent);
    }

    public void onBtnVolleyClicked(View V){
        Intent myIntent = new Intent(getApplicationContext(),VolleyActivity.class);
        startActivity(myIntent);
    }




}