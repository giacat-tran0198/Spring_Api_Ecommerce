package com.crm.gestionstock.config;

import com.crm.gestionstock.dto.AdresseDto;
import com.crm.gestionstock.dto.RolesDto;
import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.repository.RolesRepository;
import com.crm.gestionstock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataLoader implements ApplicationRunner {


    private final UtilisateurService utilisateurService;
    private final RolesRepository rolesRepository;

    @Autowired
    public DataLoader(UtilisateurService utilisateurService, RolesRepository rolesRepository) {

        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        AdresseDto adresseDto = AdresseDto.builder()
                .adresse1("adresse 1")
                .codePostale("12345")
                .ville("vile 1")
                .pays("pays 1")
                .build();

        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .nom("Cat")
                .prenom("Tran")
                .email("test@test.com")
                .motDePasse("123")
                .dateNaissance(Instant.now())
                .adresse(adresseDto)
                .build();
        UtilisateurDto savedUser = utilisateurService.save(utilisateurDto);

        RolesDto rolesDto = RolesDto.builder()
                .roleName("ADMIN")
                .utilisateur(savedUser)
                .build();

        rolesRepository.save(RolesDto.toEntity(rolesDto));
    }
}
