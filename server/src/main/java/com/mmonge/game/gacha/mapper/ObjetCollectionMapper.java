package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjetCollectionMapper {

    private final ImageMapper imageMapper;

    public ObjetCollectionMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public List<ObjetCollectionDTO> objetCollectionEntityToDtos(List<ObjetCollectionEntity> entities) {
        List<ObjetCollectionDTO> dtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entities)) {
            dtos = entities.stream().map(this::objetCollectionEntityToDto).collect(Collectors.toList());
        }
        return dtos;
    }

    public ObjetCollectionDTO objetCollectionEntityToDto(@NonNull ObjetCollectionEntity entity) {
        return new ObjetCollectionDTO(entity.getId(), entity.getCode(), RareteEnum.get(entity.getRarete()), imageMapper.imageEntityToDto(entity.getImage()), entity.getCategorie());
    }
}
