package id.jayaantara.tinggalin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tinggalin.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE tb_kontrakan(id_kontrakan INTEGER PRIMARY KEY autoincrement, nama_kontrakan TEXT NOT NULL, nama_pemilik TEXT NOT NULL, harga TEXT NOT NULL, alamat TEXT NOT NULL)";
        Log.d("Data","OnCreate: "+ SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1 ){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tb_kontrakan");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM tb_kontrakan";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, )
    }
}
