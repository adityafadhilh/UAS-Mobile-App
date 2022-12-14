package id.ac.umn.uas_mobileapp;

import java.util.Date;

public class TransaksiData{
    private String tipeTransaksi;
    private String kategori;
    private String tipeSaldo;
    private String tanggal;
    private int nominal;
    private int image;

    public TransaksiData(){}

    public void setTipeTransaksi(String tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setTipeSaldo(String tipeSaldo) {
        this.tipeSaldo = tipeSaldo;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getTipeTransaksi() {
        return tipeTransaksi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getTipeSaldo() {
        return tipeSaldo;
    }

    public int getNominal() {
        return nominal;
    }

    public String getTanggal() {
        return tanggal;
    }
}
