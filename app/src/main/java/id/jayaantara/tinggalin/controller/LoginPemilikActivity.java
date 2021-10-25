package id.jayaantara.tinggalin.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.jayaantara.tinggalin.ListKontrakanActivity;
import id.jayaantara.tinggalin.R;

public class LoginPemilikActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;

    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pemilik);

        tv_register =findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_register:
                toRegister();
                break;
            case R.id.btn_login:
                toListKontrakan();
                break;
        }
    }

    //    Register Function
    public void toRegister(){
        Intent toRegisterPemilikActivityIntent = new Intent(getApplicationContext(), RegisterPemilikActivity.class);
        startActivity(toRegisterPemilikActivityIntent);
    }

    public void toListKontrakan(){
        Intent toListKontrakanActivityIntent = new Intent(getApplicationContext(), ListKontrakanActivity.class);
        startActivity(toListKontrakanActivityIntent);
    }
}