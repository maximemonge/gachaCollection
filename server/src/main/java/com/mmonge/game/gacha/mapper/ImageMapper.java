package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ImageDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ImageMapper {

    public ImageDTO imageEntityToDto(ImageEntity entity) {
        ImageDTO dto = new ImageDTO();
        if (entity != null) {
            dto.setImage(entity.getImage());
            dto.setDescription(entity.getDescription());
            dto.setId(entity.getId());
        }
        return dto;
    }
}
