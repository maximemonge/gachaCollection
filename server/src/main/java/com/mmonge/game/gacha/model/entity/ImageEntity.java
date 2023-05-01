package com.mmonge.game.gacha.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "IMAGE")
@Getter
@Setter
public class ImageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_description")
    private String description;

    @Lob
    @Column(name = "img_image", columnDefinition = "BLOB")
    private String image;

    @OneToOne(mappedBy = "image")
    private ObjetCollectionEntity objetCollection;
}
