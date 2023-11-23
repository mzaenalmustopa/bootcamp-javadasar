package com.bootcamp.oop.service;

import com.bootcamp.oop.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KelasServiceImpl implements KelasService{
    @Override
    public List<Kelas> get() {
        List<Kelas> result = new ArrayList<>();

        Dosen dosen = new Dosen(4321,"ROni","Pria","S.Kom","D-4321");

        List<Mahasiswa> mahasiswaList = Arrays.asList(
                new Mahasiswa(123,"Siti","Wanita","TI-123","TI",2022),
                new Mahasiswa(124,"Ahmad","Pria","TI-122","TI",2022),
                new Mahasiswa(125,"Abdul","Pria","TI-122","TI",2022),
                new Mahasiswa(125,"Rina","Wanita","TI-122","TI",2022)
        );

        // kelas 1
        Matakuliah matakuliah = new Matakuliah("MK-001","Pemrograman Java",3);
        Kelas kelas1 = new Kelas("K-001", matakuliah, dosen, "Senin","19.00 - 21.00", mahasiswaList);
        result.add(kelas1);

        // kelas 2
        List<Mahasiswa> mahasiswaList2 = Arrays.asList(
                new Mahasiswa(3321,"Husni","Ciamis", LocalDate.of(2005,9,2),"Wanita","TI-123","TI",2022),
                new Mahasiswa(3322,"Todi","Medan", LocalDate.of(2000,12,2),"Pria",
                        new Alamat(2,"Jl. Medan Merdeka", "Mergosari","Mergosan","Medan","Sumatera Barat"),
                        "TI-122","TI",2022),
                new Mahasiswa(3324,"Topa","Ciamis", LocalDate.of(2001,11,2),"Pria","TI-122","TI",2022),
                new Mahasiswa(3325,"Todi","Bandung", LocalDate.of(2002,1,2),"Wanita",
                        new Alamat(1,"Jl. Pamarican", "Sukahurip","Pamarican","Ciamis","Jawa Barat"),
                        "TI-122","TI",2022)
        );
        Dosen dosen2 = new Dosen(4322,"Ahmad","Ciamis", LocalDate.of(1990,12,2), "Pria","S.Kom","D-4321");
        dosen2.setAlamat(new Alamat(3,"Jl. Jalan","Nama Desa","Nama Kecamatan","Nama Kabuputane","Nama Propinsi"));

        Matakuliah matakuliah2 = new Matakuliah("MK-002","Pemrograman Database",3);
        Kelas kelas2 = new Kelas("K-002", matakuliah2, dosen2, "Selasa","19.00 - 21.00", mahasiswaList2);
        result.add(kelas2);

        return result;
    }

    @Override
    public Kelas getById() {
        return null;
    }
}
