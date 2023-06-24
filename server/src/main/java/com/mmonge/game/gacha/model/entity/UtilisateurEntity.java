package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UTILISATEUR")
@Getter
@Setter
public class UtilisateurEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_identifiant")
    private String identifiant;

    @Column(name = "usr_mot_de_passe")
    private String motDePasse;

    @Column(name = "usr_date_creation")
    private Date dateCreation;

    @Column(name = "usr_date_modification")
    private Date dateModification;

    @Column(name = "usr_date_suppression")
    private Date dateSuppression;

    @Column(name = "usr_monnaie")
    private Long monnaie;

    @OneToMany(mappedBy = "utilisateur")
    private Set<UtilisateurCollectionEntity> ucoUsrUtilisateurCollections;
}
