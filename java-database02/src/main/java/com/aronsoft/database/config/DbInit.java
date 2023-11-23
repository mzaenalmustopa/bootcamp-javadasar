package com.aronsoft.database.config;

import com.aronsoft.database.entity.FakultasEntity;
import com.aronsoft.database.entity.JurusanEntity;
import com.aronsoft.database.repository.FakultasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {
    private FakultasRepo fakultasRepo;

    @Autowired
    public DbInit(FakultasRepo fakultasRepo) {
        this.fakultasRepo = fakultasRepo;
    }

    private void initFakultas(){
        FakultasEntity fakultas = new FakultasEntity("FMIPA","Fakultas Matematika dan IPA","Yogyakarta");
        fakultas.addJurusan(new JurusanEntity("MTK","Matematika"));
        fakultas.addJurusan(new JurusanEntity("FIK","Fisika"));
        fakultas.addJurusan(new JurusanEntity("BIO","Biologi"));
        fakultas.addJurusan(new JurusanEntity("SNC","Since"));

        this.fakultasRepo.save(fakultas);
    }

    @Override
    public void run(String... args) throws Exception {
       // initFakultas();
    }
}
