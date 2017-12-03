package com.example.indraarianggi.inventorikaryawan.adapterKaryawan;

/**
 * Created by indraarianggi on 04/12/17.
 */

public class Karyawan {

    private long id;
    private String nama;
    private String email;
    private String deplop;
    private String perusahaan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeplop() {
        return deplop;
    }

    public void setDeplop(String deplop) {
        this.deplop = deplop;
    }

    public String getPerusahaan() {
        return perusahaan;
    }

    public void setPerusahaan(String perusahaan) {
        this.perusahaan = perusahaan;
    }

    @Override
    public String toString() {
        return "Karyawan" +nama+ " " +email+ "" +deplop+ " " +perusahaan+ " ";
    }
}
