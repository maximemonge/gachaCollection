package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ImageDTO;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ObjetCollectionMapper {

    private ImageMapper imageMapper;

    public List<ObjetCollectionDTO> objetCollectionEntityToDtos(List<ObjetCollectionEntity> entities) {
        List<ObjetCollectionDTO> dtos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(entities)) {
            dtos = entities.stream().filter(Objects::nonNull).map(this::objetCollectionEntityToDto).collect(Collectors.toList());
        }
        return dtos;
    }

    private ObjetCollectionDTO objetCollectionEntityToDto(ObjetCollectionEntity entity) {
        ObjetCollectionDTO dto = new ObjetCollectionDTO();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setRarete(RareteEnum.get(entity.getRarete()));
            dto.setImage(imageMapper.imageEntityToDto(entity.getImage()));
            dto.setCategorie(entity.getCategorie());
        }
        return dto;
    }

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }
}
