package id.jayaantara.tinggalin;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kontrakan {

    @PrimaryKey
    public int id_kontrakan;


    public String nama_kontrakan;
    public String nama_pemilik;
    public String harga;
    public String alamat;

}
