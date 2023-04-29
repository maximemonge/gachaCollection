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
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_pseudo")
    private String pseudo;

    @Column(name = "usr_mot_de_passe")
    private String motDePasse;

    @Column(name = "usr_date_creation")
    private Date dateCreation;

    @Column(name = "usr_date_modification")
    private Date dateModification;

    @Column(name = "usr_date_suppression")
    private Date dateSuppression;

    @ManyToMany
    @JoinTable(name = "utilisateur_collection", joinColumns = @JoinColumn(name = "uco_usr_id"), inverseJoinColumns = @JoinColumn(name = "uco_oac_id"))
    private Set<ObjetCollectionEntity> objetsCollectionnes;
}
