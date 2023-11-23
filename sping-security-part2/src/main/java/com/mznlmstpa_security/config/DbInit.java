package com.mznlmstpa_security.config;


import com.mznlmstpa_security.entity.ProductEntity;
import com.mznlmstpa_security.entity.RoleEntity;
import com.mznlmstpa_security.entity.UserEntity;
import com.mznlmstpa_security.repository.ProductRepo;
import com.mznlmstpa_security.repository.RoleRepo;
import com.mznlmstpa_security.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DbInit implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepo productRepo;

    @Override
    public void run(String... args) throws Exception {

        initUserAndRole();
        intiProduct();

    }

    private void initUserAndRole() {
        if (roleRepo.findAll().isEmpty()) {
            try {
                this.roleRepo.saveAll(
                        Arrays.asList(
                                new RoleEntity(UUID.randomUUID().toString(), "ROLE_USER"),
                                new RoleEntity(UUID.randomUUID().toString(), "ROLE_ADMIN"),
                                new RoleEntity(UUID.randomUUID().toString(), "ROLE_SUPER_USER")
                        )
                );
                log.info("Save Role is Success");
            } catch (Exception e) {
                log.error("Save Role is Failed, error{}", e.getMessage());
            }
        }
        if (userRepo.findAll().isEmpty()) {
            Optional<RoleEntity> suRole = roleRepo.findByName("ROLE_SUPER_USER");
            if (suRole.isPresent()) {
                this.userRepo.save(new UserEntity(UUID.randomUUID().toString(), "Super User", "super.user@gmail.com",
                        passwordEncoder.encode("password"), Arrays.asList(suRole.get())));
            }

            Optional<RoleEntity> userRole = roleRepo.findByName("ROLE_USER");
            userRole.ifPresent(roleEntity ->this.userRepo.save( new UserEntity(UUID.randomUUID().toString(), "User", "user@gmail.com",
                    passwordEncoder.encode("password"), Arrays.asList(userRole.get()))));

            Optional<RoleEntity> adminRole = roleRepo.findByName("ROLE_ADMIN");
            adminRole.ifPresent(roleEntity -> this.userRepo.save(new UserEntity(UUID.randomUUID().toString(), "Admin", "admin@gmail.com",
                    passwordEncoder.encode("password"), Arrays.asList(adminRole.get()))));
        }
    }

    private void intiProduct(){
        if (productRepo.findAll().isEmpty()){
            this.productRepo.saveAll(
                    Arrays.asList(
                            new ProductEntity(UUID.randomUUID().toString(),"P001","Cokelat",12000.0,100.0),
                            new ProductEntity(UUID.randomUUID().toString(),"P002","Keju",16000.0,100.0),
                            new ProductEntity(UUID.randomUUID().toString(),"P003","Selai",19000.0,100.0),
                            new ProductEntity(UUID.randomUUID().toString(),"P004","Ice Cream",20000.0,100.0),
                            new ProductEntity(UUID.randomUUID().toString(),"P005","Cokelat Keju",25000.0,100.0),
                            new ProductEntity(UUID.randomUUID().toString(),"P006","Rujak",10000.0,100.0)
                    )
            );
        }
    }
}
