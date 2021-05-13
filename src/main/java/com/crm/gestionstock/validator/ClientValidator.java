package com.crm.gestionstock.validator;

import com.crm.gestionstock.dto.ClientDto;
import com.crm.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {
    public static List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();
        if (clientDto == null) {
            errors.add("Veuillez renseigner le nom de client");
            errors.add("Veuillez renseigner le prénom de client");
            errors.add("Veuillez renseigner l'email de client");
            errors.add("Veuillez renseigner le numero de telephone de client");
        } else {
            if (!StringUtils.hasLength(clientDto.getNom())) {
                errors.add("Veuillez renseigner le nom de client");
            }
            if (!StringUtils.hasLength(clientDto.getPrenom())) {
                errors.add("Veuillez renseigner le prénom de client");
            }
            if (!StringUtils.hasLength(clientDto.getMail())) {
                errors.add("Veuillez renseigner l'email de client");
            }
            if (!StringUtils.hasLength(clientDto.getNumTel())) {
                errors.add("Veuillez renseigner le numero de telephone de client");
            }
        }
        return errors;
    }
}
