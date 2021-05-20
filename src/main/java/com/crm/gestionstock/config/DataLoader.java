package com.crm.gestionstock.config;

import com.crm.gestionstock.dto.EntrepriseDto;
import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.model.Adresse;
import com.crm.gestionstock.model.Entreprise;
import com.crm.gestionstock.model.Utilisateur;
import com.crm.gestionstock.repository.UtilisateurRepository;
import com.crm.gestionstock.services.EntrepriseService;
import com.crm.gestionstock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final EntrepriseService entrepriseService;

    @Autowired
    public DataLoader(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Entreprise entreprise = new Entreprise();
        entreprise.setNom("entreprise 1");
        entreprise.setDescription("Entreprise 1");
        entreprise.setCodeFiscal("12334");
        entreprise.setEmail("test@test.com");
        entreprise.setNumTel("123456789");

        Adresse adresse = new Adresse();
        adresse.setAdresse1("12 AB");
        adresse.setCodePostale("123");
        adresse.setVille("123");
        adresse.setPays("france");

        entreprise.setAdresse(adresse);
        entrepriseService.save(EntrepriseDto.fromEntity(entreprise));
    }
}
