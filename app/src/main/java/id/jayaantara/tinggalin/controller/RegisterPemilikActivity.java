package id.jayaantara.tinggalin.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import id.jayaantara.tinggalin.DataHelper;
import id.jayaantara.tinggalin.R;

public class RegisterPemilikActivity extends AppCompatActivity implements View.OnClickListener {

    protected Cursor cursor;
    DataHelper db_helper;

    private EditText email;
    private EditText username;
    private EditText password;
    private EditText confirm_password;
    private EditText age;


    private SeekBar sb_age;

    private CheckBox term_condition;

    private RadioButton rb_gender_male;
    private RadioButton rb_gender_female;
    private RadioGroup rb_group_gender;

    private Button btn_regist;
    private Button btn_cancel;

    public static final String EXTRA_MESSAGE_USERNAME= "id.jayaantara.tinggalin.controller.MESSAGE_USERNAME";
    public static final String EXTRA_MESSAGE_AGE= "id.jayaantara.tinggalin.controller.MESSAGE_AGE";
    public static final String EXTRA_MESSAGE_GENDER= "id.jayaantara.tinggalin.controller.MESSAGE_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pemilik);

        email = (EditText) findViewById(R.id.edt_email);
        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);
        confirm_password = (EditText) findViewById(R.id.edt_con_password);
        age = (EditText) findViewById(R.id.edt_age);


        sb_age = (SeekBar) findViewById(R.id.sb_age);
        sb_age.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                age.setText(Integer.toString(progressChangedValue));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                age.setText(Integer.toString(progressChangedValue));
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                age.setText(Integer.toString(progressChangedValue));
            }
        });


        term_condition = (CheckBox) findViewById(R.id.cb_term);

        rb_group_gender = (RadioGroup) findViewById(R.id.rb_group_gender);
        rb_gender_male = (RadioButton) findViewById(R.id.rb_male);
        rb_gender_female = (RadioButton) findViewById(R.id.rb_female);

        btn_regist = findViewById(R.id.btn_regist);
        btn_regist.setOnClickListener(this);
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                resetData();
                break;
            case R.id.btn_regist:
                if(email.length() == 0 ){
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum mengisi email", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else if(username.length() == 0 ){
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum mengisi username", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                }else if(password.length() == 0 ){
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum mengisi password", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }else if(confirm_password.length() == 0 ){
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum mengkonfirmasi password", Toast.LENGTH_SHORT).show();
                    confirm_password.requestFocus();
                }else if(Integer.parseInt(age.getText().toString()) < 1){
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum memilih umur", Toast.LENGTH_SHORT).show();
                    rb_group_gender.requestFocus();
                }else if(rb_group_gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(RegisterPemilikActivity.this, "Anda belum memilih gender", Toast.LENGTH_SHORT).show();
                }else{
                    String check_pass = confirm_password.getText().toString();
                    if(password.getText().toString().equals(check_pass)){
                        if (term_condition.isChecked()){
                            confirmRegist();
                        }else{
                            Toast.makeText(RegisterPemilikActivity.this, "Anda belum menyetujui syarat & ketentuan", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Konfirmasi password salah, harap gunakan password yang sama", Toast.LENGTH_SHORT).show();
                        confirm_password.requestFocus();
                    }
                }
                break;
        }
    }

    public void resetData(){
        email.setText(null);
        username.setText(null);
        password.setText(null);
        confirm_password.setText(null);
        rb_group_gender.clearCheck();
        age.setText(null);
        sb_age.setProgress(0);
        term_condition.setChecked(false);
    }

    public void confirmRegist(){
        TextView regist_email;
        TextView regist_username;
        TextView regist_password;
        TextView regist_gender;
        TextView regist_age;
        TextView regist_term;

        Button btn_regist_popup;
        Button btn_cancel_popup;

        final Dialog dialog = new Dialog(RegisterPemilikActivity.this);

        dialog.setContentView(R.layout.regist_popup_menu);

        regist_email = (TextView) dialog.findViewById(R.id.regist_email);
        regist_email.setText(email.getText().toString());

        regist_username = (TextView) dialog.findViewById(R.id.regist_username);
        regist_username.setText(username.getText().toString());

        regist_password = (TextView) dialog.findViewById(R.id.regist_password);
        regist_password.setText(password.getText().toString());

        regist_gender = (TextView) dialog.findViewById(R.id.regist_gender);
        int id_selection = rb_group_gender.getCheckedRadioButtonId();
        RadioButton selection_gender = (RadioButton) findViewById(id_selection);
        regist_gender.setText(selection_gender.getText().toString());

        regist_age = (TextView) dialog.findViewById(R.id.regist_age);
        regist_age.setText(age.getText().toString());

        regist_term = (TextView) dialog.findViewById(R.id.regist_term);
        if (term_condition.isChecked()){
            regist_term.setText("Setuju");
        }

        btn_cancel_popup = dialog.findViewById(R.id.btn_cancel_popup);
        btn_cancel_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_regist_popup = dialog.findViewById(R.id.btn_regist_popup);
        btn_regist_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = db_helper.getWritableDatabase();
                db.execSQL("INSERT INTO tb_pemilik(email,username,password,gender,age) VALUES('"+
                            regist_email+ "','"+
                            regist_username+ "','"+
                            regist_gender+ "','"+
                            regist_age+ "')");
                Toast.makeText(RegisterPemilikActivity.this, "Berhasil Mendaftarkan", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(RegisterPemilikActivity.this, ProfilPemilikActivity.class);
                intent.putExtra(EXTRA_MESSAGE_USERNAME, regist_username.getText().toString());
                intent.putExtra(EXTRA_MESSAGE_AGE, regist_age.getText().toString());
                intent.putExtra(EXTRA_MESSAGE_GENDER, regist_gender.getText().toString());
                startActivity(intent);
            }
        });

        dialog.show();

    }
}