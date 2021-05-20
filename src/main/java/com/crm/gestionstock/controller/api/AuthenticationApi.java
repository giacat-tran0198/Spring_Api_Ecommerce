package com.crm.gestionstock.controller.api;

import com.crm.gestionstock.dto.auth.AuthenticationRequest;
import com.crm.gestionstock.dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.crm.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/authenticate")
public interface AuthenticationApi {
    @PostMapping(value = APP_ROOT + "/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
