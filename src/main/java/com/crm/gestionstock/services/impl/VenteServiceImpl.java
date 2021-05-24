package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.ArticleDto;
import com.crm.gestionstock.dto.LigneVenteDto;
import com.crm.gestionstock.dto.MvtStkDto;
import com.crm.gestionstock.dto.VenteDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.model.Article;
import com.crm.gestionstock.model.LigneVente;
import com.crm.gestionstock.model.Vente;
import com.crm.gestionstock.model.enums.SourceMvtStk;
import com.crm.gestionstock.model.enums.TypeMvtStk;
import com.crm.gestionstock.repository.ArticleRepository;
import com.crm.gestionstock.repository.LigneVenteRepository;
import com.crm.gestionstock.repository.VenteRepository;
import com.crm.gestionstock.services.MvtStkService;
import com.crm.gestionstock.services.VenteService;
import com.crm.gestionstock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {
    private final ArticleRepository articleRepository;
    private final VenteRepository venteRepository;
    private final LigneVenteRepository ligneVenteRepository;
    private final MvtStkService mvtStkService;

    @Autowired
    public VenteServiceImpl(ArticleRepository articleRepository,
                            VenteRepository venteRepository,
                            LigneVenteRepository ligneVenteRepository,
                            MvtStkService mvtStkService) {
        this.articleRepository = articleRepository;
        this.venteRepository = venteRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Sale is not valid");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        List<String> articleErrors = new ArrayList<>();
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a été trouvé dans la BDD");
            }
        });
        if (!articleErrors.isEmpty()) {
            log.error("One or more articles were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas été trouvé dans BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });

        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return null;
        }

        return venteRepository
                .findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                                "Aucun vente avec l'ID = " + id + " n'été trouvé dans la BDD",
                                ErrorCodes.VENTE_NOT_FOUND
                        )
                );
    }

    @Override
    public VenteDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente ID is NULL");
            return null;
        }

        return venteRepository
                .findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                                "Aucun vente avec le CODE = " + code + " n'été trouvé dans la BDD",
                                ErrorCodes.VENTE_NOT_FOUND
                        )
                );
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository
                .findAll()
                .stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return;
        }
        venteRepository.deleteById(id);
    }
    private void updateMvtStk(LigneVente lig) {
        MvtStkDto mvtStkDto = MvtStkDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typeMvt(TypeMvtStk.SORTIE)
                .sourceMvt(SourceMvtStk.VENTE)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        mvtStkService.sortieStock(mvtStkDto);
    }
}
