package id.ac.umn.uas_mobileapp;

import java.util.Date;

public class TransaksiData{
    private String tipeTransaksi;
    private String kategori;
    private String tipeSaldo;
    private int nominal;
    private Date date;

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

    public void setDate(Date date) {
        this.date = date;
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

    public Date getDate() {
        return date;
    }
}
