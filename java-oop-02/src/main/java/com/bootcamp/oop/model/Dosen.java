package com.bootcamp.oop.model;

import java.time.LocalDate;

public class Dosen extends Orang{
    private String title;
    private String nip;

    public Dosen() {
    }

    public Dosen(int nik, String nama, String jk, String title, String nip) {
        super(nik, nama, jk);
        this.title = title;
        this.nip = nip;
    }

    public Dosen(int nik, String nama, String tptLahir, LocalDate tglLahir, String jk, String title, String nip) {
        super(nik, nama, tptLahir, tglLahir, jk);
        this.title = title;
        this.nip = nip;
    }

    public Dosen(int nik, String nama, String tptLahir, LocalDate tglLahir, String jk, Alamat alamat, String title, String nip) {
        super(nik, nama, tptLahir, tglLahir, jk, alamat);
        this.title = title;
        this.nip = nip;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
