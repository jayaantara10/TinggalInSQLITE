package id.jayaantara.tinggalin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormKontrakanActivity extends AppCompatActivity implements View.OnClickListener {

    private AppDatabase database;
    private EditText edt_kontrakan_name;
    private EditText edt_owner_name;
    private EditText edt_harga;
    private EditText edt_alamat;

    private Button btn_cancel;
    private Button btn_tambah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kontrakan);

        database = AppDatabase.getInstance(getApplicationContext());

        edt_kontrakan_name = findViewById(R.id.edt_kontrakan_name);
        edt_owner_name = findViewById(R.id.edt_owner_name);
        edt_harga = findViewById(R.id.edt_harga_sewa);
        edt_alamat = findViewById(R.id.edt_alamat_kontrakan);

        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                kembali();
                break;
            case R.id.btn_tambah:
                tambahData();
                break;
        }

    }

    private void tambahData() {

        database.kontrakanDao().insertAll(edt_kontrakan_name.getText().toString(), edt_owner_name.getText().toString(), edt_harga.getText().toString(), edt_alamat.getText().toString());

        Intent toListKontrakanActivityIntent = new Intent(getApplicationContext(), ListKontrakanActivity.class);
        startActivity(toListKontrakanActivityIntent);
    }

    public void kembali(){
        startActivity(new Intent(FormKontrakanActivity.this, ListKontrakanActivity.class));
    }
}