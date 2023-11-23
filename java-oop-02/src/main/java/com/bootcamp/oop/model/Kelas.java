package com.bootcamp.oop.model;

import java.util.List;

public class Kelas {
    private Integer id;
    private String kode;
    private Matakuliah matakuliah;
    private Dosen dosen;
    private String hari;
    private String jadwal;
    private List<Mahasiswa> mahasiswa;

    public Kelas() {
    }

    public Kelas(String kode, Matakuliah matakuliah, Dosen dosen, String hari, String jadwal, List<Mahasiswa> mahasiswa) {
        this.kode = kode;
        this.matakuliah = matakuliah;
        this.dosen = dosen;
        this.hari = hari;
        this.jadwal = jadwal;
        this.mahasiswa = mahasiswa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Matakuliah getMatakuliah() {
        return matakuliah;
    }

    public void setMatakuliah(Matakuliah matakuliah) {
        this.matakuliah = matakuliah;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public List<Mahasiswa> getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(List<Mahasiswa> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}
