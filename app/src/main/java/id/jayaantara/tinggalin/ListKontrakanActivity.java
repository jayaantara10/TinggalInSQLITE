package id.jayaantara.tinggalin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListKontrakanActivity extends AppCompatActivity{

    private AppDatabase database;
    private RecyclerView recyclerView;
    private FloatingActionButton btn_tambah;
    private KontrakanAdapter kontrakanAdapter;
    private List<Kontrakan> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kontrakan);



        recyclerView = findViewById(R.id.recycleview_kontrakan_data);
        database = AppDatabase.getInstance(getApplicationContext());

        list.clear();
        list.addAll(database.kontrakanDao().getAll());
        kontrakanAdapter = new KontrakanAdapter(getApplicationContext(), list);



        btn_tambah = findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListKontrakanActivity.this, FormKontrakanActivity.class));
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(kontrakanAdapter);
        kontrakanAdapter.setDialog(new KontrakanAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem ={"Edit","Hapus"};
                dialog = new AlertDialog.Builder(ListKontrakanActivity.this);
                dialog.setItems(dialogItem,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
//                                toEditData();
                                break;
                            case 1:
                                Kontrakan kontrakan = list.get(position);
                                database.kontrakanDao().delete(kontrakan);
                                onStart();
                                break;

                        }
                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        list.clear();
        list.addAll(database.kontrakanDao().getAll());
        kontrakanAdapter.notifyDataSetChanged();
    }

//    public void toEditData(){
//        Intent toFormKontrakanActivityIntent = new Intent(getApplicationContext(), FormKontrakanActivity.class);
//        toFormKontrakanActivityIntent.putExtra("id_kontrakan", list.get(position).id_kontrakan);
//        startActivity(toFormKontrakanActivityIntent);
//    }
}