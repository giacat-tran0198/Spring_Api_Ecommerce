package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.CategoryDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.repository.CategoryRepository;
import com.crm.gestionstock.services.CategoryService;
import com.crm.gestionstock.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        List<String> errors = CategoryValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid {}", dto);
            throw new InvalidEntityException("La categorie n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID);
        }
        return CategoryDto.fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(dto)
                )
        );
    }

    @Override
    public CategoryDto findById(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return null;
        }
        return categoryRepository
                .findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                                "Aucun categorie avec l'ID = " + id + " n'été trouvé dans la BDD",
                                ErrorCodes.CATEGORY_NOT_FOUND
                        )
                );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (code == null) {
            log.error("Category CODE is null");
            return null;
        }
        return categoryRepository
                .findCategoryByCode(code)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aucun categorie avec le CODE = " + code + " n'été trouvé dans la BDD",
                                ErrorCodes.CATEGORY_NOT_FOUND)
                );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Category ID is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
