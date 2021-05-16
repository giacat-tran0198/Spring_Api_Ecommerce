package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.EntrepriseDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.model.Entreprise;
import com.crm.gestionstock.repository.EntrepriseRepository;
import com.crm.gestionstock.services.EntrepriseService;
import com.crm.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto dto) {
        List<String> errors = EntrepriseValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Enreprise is not valid {}", dto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID);
        }
        return EntrepriseDto.fromEntity(
                entrepriseRepository.save(
                        EntrepriseDto.toEntity(dto)
                )
        );
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null) {
            log.error("Enreprise ID is null");
            return null;
        }
        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        return Optional
                .ofNullable(
                        EntrepriseDto.fromEntity(
                                entreprise.orElse(null)
                        )
                )
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
