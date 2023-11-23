package com.aronsoft.webmvc.config;

import com.aronsoft.webmvc.entity.*;
import com.aronsoft.webmvc.repository.FakultasRepo;
import com.aronsoft.webmvc.service.LookupService;
import com.aronsoft.webmvc.service.RoleService;
import com.aronsoft.webmvc.service.UserService;
import com.aronsoft.webmvc.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbInit implements CommandLineRunner {
    private final FakultasRepo fakultasRepo;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final RoleService roleService;
    private final LookupService lookupService;

    @Autowired
    public DbInit(FakultasRepo fakultasRepo, PasswordEncoder encoder, UserService userService, RoleService roleService,
                  LookupService lookupService) {
        this.fakultasRepo = fakultasRepo;
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
        this.lookupService = lookupService;
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

    private void initUserAndRole(){
        if(roleService.getCount() == 0){
            this.roleService.save(Arrays.asList(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_DOSEN"),
                    new RoleEntity("ROLE_MAHASISWA"),
                    new RoleEntity("ROLE_KEUANGAN")
            ));
        }

        if(userService.getCount()==0){
            RoleEntity adminRole = roleService.getByName("ROLE_ADMIN");
            UserEntity admin = new UserEntity("admin", encoder.encode("admin32!"),"admin@gmail.com", Arrays.asList(adminRole));
            this.userService.save(admin);

            RoleEntity mhsRole = roleService.getByName("ROLE_MAHASISWA");
            UserEntity mhs = new UserEntity("mahasiswa", encoder.encode("mahasiswa32!"),"mahasiswa@gmail.com", Arrays.asList(mhsRole));
            this.userService.save(mhs);

            RoleEntity dosenRole = roleService.getByName("ROLE_DOSEN");
            UserEntity dosen = new UserEntity("dosen", encoder.encode("dosen32!"),"dosen@gmail.com", Arrays.asList(dosenRole));
            this.userService.save(dosen);
        }
    }

    private void initLookup(){
        if(lookupService.getByGroup("HARI").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookupEntity("HARI","SENIN","Senin",1),
                    new LookupEntity("HARI","SELASA","Selasa",2),
                    new LookupEntity("HARI","RABU","RABU",3),
                    new LookupEntity("HARI","KAMIS","Kamis",4),
                    new LookupEntity("HARI","JUMAT","Jumat",5),
                    new LookupEntity("HARI","SABTU","Sabtu",6),
                    new LookupEntity("HARI","MINGGU","Minggu",7)
            ));
        }

        if(lookupService.getByGroup("SEMESTER").isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookupEntity("SEMESTER","GANJIL","Ganjil",1),
                    new LookupEntity("SEMESTER","GANJIL","Genap",2)
            ));
        }

        if(lookupService.getByGroup(Constants.GENDER).isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookupEntity("GENDER","PRIA","Pria",1),
                    new LookupEntity("GENDER","WANITA","Wanita",2)
            ));
        }

        if(lookupService.getByGroup(Constants.AGAMA).isEmpty()){
            lookupService.saveAll(Arrays.asList(
                    new LookupEntity("AGAMA","ISLAM","Islam",1),
                    new LookupEntity("AGAMA","KRISTEN","Kristen",2),
                    new LookupEntity("AGAMA","KATHOLIK","Katholik",3),
                    new LookupEntity("AGAMA","HINDU","Hindu",4),
                    new LookupEntity("AGAMA","BUDHA","Budha",5),
                    new LookupEntity("AGAMA","KONGHUCHU","Konghuchu",6),
                    new LookupEntity("AGAMA","LAINNYA","Lainnya",7)
            ));
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initFakultas();
        // panggil method generate role user
        initUserAndRole();

        initLookup();
    }
}
