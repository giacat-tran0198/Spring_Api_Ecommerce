package com.crm.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse{
    @Column(name = "address1")
    private String adresse1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code")
    private String codePostale;

    @Column(name = "pays")
    private String pays;
}
