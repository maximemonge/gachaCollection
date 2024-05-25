package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "OBJET_A_COLLECTIONNER")
public class ObjetCollectionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oac_id")
    private Long id;

    @Column(name = "oac_code")
    private String code;

    @Column(name = "oac_rarete")
    private String rarete;

    @Column(name = "oac_categorie")
    private String categorie;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "oac_img_id", referencedColumnName = "img_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "objetCollection")
    private Set<UtilisateurCollectionEntity> possesseurs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRarete() {
        return rarete;
    }

    public void setRarete(String rarete) {
        this.rarete = rarete;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public Set<UtilisateurCollectionEntity> getPossesseurs() {
        return possesseurs;
    }

    public void setPossesseurs(Set<UtilisateurCollectionEntity> possesseurs) {
        this.possesseurs = possesseurs;
    }
}
