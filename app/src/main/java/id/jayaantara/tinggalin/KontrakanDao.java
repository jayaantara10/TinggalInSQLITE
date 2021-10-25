package id.jayaantara.tinggalin;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KontrakanDao {

    @Query("SELECT * FROM kontrakan")
    List<Kontrakan> getAll();

    @Query("INSERT INTO kontrakan (nama_kontrakan, nama_pemilik, harga, alamat) VALUES(:nama_kontrakan, :nama_pemilik, :harga, :alamat)")
    void insertAll(String nama_kontrakan, String nama_pemilik, String harga, String alamat);

//    @Query("UPDATE kontrakan SET nama_kontrakan = :nama_kontrakan, nama_pemilik = :nama_pemilik, harga = :harga, alamat = :alamat WHERE id_kontrakan = :id_kontrakan")
//    void edit(int id_kontrakan, String nama_kontrakan, String nama_pemilik, String harga, String alamat);

    @Delete
    void delete(Kontrakan kontrakan);
}
