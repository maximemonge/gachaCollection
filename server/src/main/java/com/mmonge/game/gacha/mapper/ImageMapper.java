package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ImageDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class ImageMapper {

    public ImageDTO imageEntityToDto(@NonNull ImageEntity entity) {
        return new ImageDTO(entity.getId(), entity.getDescription(), entity.getImage());
    }
}
