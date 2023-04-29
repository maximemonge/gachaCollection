package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "IMAGE")
public class ImageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private Long description;

    @Column(name = "IMAGE")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDescription() {
        return description;
    }

    public void setDescription(Long description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
