package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "IMAGE")
public class ImageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_description")
    private String description;

    @Lob
    @Column(name = "img_image", columnDefinition = "BLOB")
    private String image;

    @OneToOne(mappedBy = "image")
    private ObjetCollectionEntity objetCollection;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ObjetCollectionEntity getObjetCollection() {
        return objetCollection;
    }

    public void setObjetCollection(ObjetCollectionEntity objetCollection) {
        this.objetCollection = objetCollection;
    }
}
