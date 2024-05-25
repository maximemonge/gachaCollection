package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UTILISATEUR")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Date getDateSuppression() {
        return dateSuppression;
    }

    public void setDateSuppression(Date dateSuppression) {
        this.dateSuppression = dateSuppression;
    }

    public Long getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(Long monnaie) {
        this.monnaie = monnaie;
    }

    public Set<UtilisateurCollectionEntity> getUcoUsrUtilisateurCollections() {
        return ucoUsrUtilisateurCollections;
    }

    public void setUcoUsrUtilisateurCollections(Set<UtilisateurCollectionEntity> ucoUsrUtilisateurCollections) {
        this.ucoUsrUtilisateurCollections = ucoUsrUtilisateurCollections;
    }
}
