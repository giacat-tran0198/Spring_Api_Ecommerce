package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.EntrepriseDto;
import com.crm.gestionstock.dto.RolesDto;
import com.crm.gestionstock.dto.UtilisateurDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.repository.EntrepriseRepository;
import com.crm.gestionstock.repository.RolesRepository;
import com.crm.gestionstock.services.EntrepriseService;
import com.crm.gestionstock.services.UtilisateurService;
import com.crm.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(rollbackOn = Exception.class)
@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;
    private final UtilisateurService utilisateurService;
    private final RolesRepository rolesRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
                                 RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Enreprise is not valid {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto)
                )
        );
        // Test
//        UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);
//
//        UtilisateurDto savedUser = utilisateurService.save(utilisateur);
//
//        RolesDto rolesDto = RolesDto.builder()
//                .roleName("ADMIN")
//                .utilisateur(savedUser)
//                .build();
//
//        rolesRepository.saverolesRepository.save(RolesDto.toEntity(rolesDto));

        return savedEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
                .adresse(dto.getAdresse())
                .nom(dto.getNom())
                .prenom(dto.getCodeFiscal())
                .email(dto.getEmail())
                .motDePasse(generateRandomPassword())
                .entreprise(dto)
                .dateNaissance(Instant.now())
                .photo(dto.getPhoto())
                .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }


    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Enreprise ID is null");
            return null;
        }
        return entrepriseRepository
                .findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aucun entreprise avec l'ID = " + id + " n'été trouvé dans la BDD",
                                ErrorCodes.ENTREPRISE_NOT_FOUND)
                );
    }


    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository
                .findAll()
                .stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Enreprise ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
