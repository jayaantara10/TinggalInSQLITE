package id.jayaantara.tinggalin.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import id.jayaantara.tinggalin.R;

public class ProfilPemilikActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView tv_username;
    private TextView tv_age;
    private TextView tv_gender;

    private Button btn_back;
    private Button btn_edit;

    private String username;
    private String gender;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pemilik);

        Intent intent = getIntent();
        username = intent.getStringExtra(RegisterPemilikActivity.EXTRA_MESSAGE_USERNAME);
        age = intent.getStringExtra(RegisterPemilikActivity.EXTRA_MESSAGE_AGE);
        gender = intent.getStringExtra(RegisterPemilikActivity.EXTRA_MESSAGE_GENDER);

        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_username.setText(username);

        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_gender.setText(gender);

        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_age.setText(age);


    }




    @Override
    public void onClick(View v) {

    }

    public void onStart(){
        Toast.makeText(ProfilPemilikActivity.this, "Selamat datang "+username, Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Disini kamu bisa lihat profil kamu ",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Okey, segera kembali ya aku tunggu ",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Sampai jumpa lagi "+username,Toast.LENGTH_SHORT).show();
    }
}