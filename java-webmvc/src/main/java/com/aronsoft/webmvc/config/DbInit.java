package com.aronsoft.webmvc.config;

import com.aronsoft.webmvc.entity.FakultasEntity;
import com.aronsoft.webmvc.entity.JurusanEntity;
import com.aronsoft.webmvc.repository.FakultasRepo;
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
        if(fakultasRepo.count() == 0) {
            FakultasEntity fakultas = new FakultasEntity("FMIPA", "Fakultas Matematika dan IPA", "Yogyakarta");
            fakultas.addJurusan(new JurusanEntity("MTK", "Matematika"));
            fakultas.addJurusan(new JurusanEntity("FIK", "Fisika"));
            fakultas.addJurusan(new JurusanEntity("BIO", "Biologi"));
            fakultas.addJurusan(new JurusanEntity("SNC", "Since"));

            this.fakultasRepo.save(fakultas);

            FakultasEntity fakultas2 = new FakultasEntity("FK", "Kedokteran", "Yogyakarta");
            fakultas2.addJurusan(new JurusanEntity("KG", "Kedokteran Gigi"));
            fakultas2.addJurusan(new JurusanEntity("KJ", "Kedokteran Jiwa"));
            fakultas2.addJurusan(new JurusanEntity("KH", "Kedokteran Hewan"));
            fakultas2.addJurusan(new JurusanEntity("KC", "Kedokteran Cinta"));

            this.fakultasRepo.save(fakultas2);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initFakultas();
    }
}
