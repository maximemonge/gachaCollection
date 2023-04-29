package com.mmonge.game.gacha.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImageDTO implements Serializable {
    private Long id;
    private String description;
    private byte[] image;
}
