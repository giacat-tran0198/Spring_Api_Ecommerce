package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.FournisseurDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.exception.InvalidOperationException;
import com.crm.gestionstock.model.CommandeClient;
import com.crm.gestionstock.model.CommandeFournisseur;
import com.crm.gestionstock.model.Fournisseur;
import com.crm.gestionstock.repository.CommandeFournisseurRepository;
import com.crm.gestionstock.repository.FournisseurRepository;
import com.crm.gestionstock.services.FournisseurService;
import com.crm.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository fournisseurRepository;
    private final CommandeFournisseurRepository commandeFournisseurRepository;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto dto) {
        List<String> errors = FournisseurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Supplier is not valid {}", dto);
            throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }
        return FournisseurDto.fromEntity(
                fournisseurRepository.save(
                        FournisseurDto.toEntity(dto)
                )
        );
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Supplier ID is null");
            return null;
        }
        return fournisseurRepository
                .findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aucun fournisseur avec l'ID = " + id + " n'été trouvé dans la BDD",
                                ErrorCodes.FOURNISSEUR_NOT_FOUND)
                );
    }


    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository
                .findAll()
                .stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Supplier ID is null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseur = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseur.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un fournisseur qui a deja des commandes",
                    ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
