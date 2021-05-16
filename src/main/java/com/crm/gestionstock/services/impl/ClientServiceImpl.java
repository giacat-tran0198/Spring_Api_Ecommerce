package com.crm.gestionstock.services.impl;

import com.crm.gestionstock.dto.ClientDto;
import com.crm.gestionstock.exception.EntityNotFoundException;
import com.crm.gestionstock.exception.ErrorCodes;
import com.crm.gestionstock.exception.InvalidEntityException;
import com.crm.gestionstock.model.Client;
import com.crm.gestionstock.repository.ClientRepository;
import com.crm.gestionstock.services.ClientService;
import com.crm.gestionstock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto dto) {
        List<String> errors = ClientValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", dto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID);
        }
        return ClientDto.fromEntity(
                clientRepository.save(
                        ClientDto.toEntity(dto)
                )
        );
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Client ID is null");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional
                .ofNullable(
                        ClientDto.fromEntity(
                                client.orElse(null)
                        )
                )
                .orElseThrow(() ->
                        new EntityNotFoundException("Aucun client avec l'ID = " + id + " n'été trouvé dans la BDD",
                                ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }
    

    @Override
    public List<ClientDto> findAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);
    }
}
