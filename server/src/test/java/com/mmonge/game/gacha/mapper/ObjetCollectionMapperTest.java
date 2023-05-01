package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ObjetCollectionMapperTest {

    @Spy
    ImageMapper imageMapper;
    @InjectMocks
    ObjetCollectionMapper objetCollectionMapper;

    @Test
    public void test_objetCollectionEntityToDtos_ok() {
        ObjetCollectionEntity objetCollectionEntityAvecImage = creerObjetCollection(1L, "Ariel", "C", creerImage());
        ObjetCollectionEntity objetCollectionEntitySansImage = creerObjetCollection(2L, "Jafar", "R", null);

        List<ObjetCollectionEntity> objetCollectionEntities = new ArrayList<>();
        objetCollectionEntities.add(objetCollectionEntityAvecImage);
        objetCollectionEntities.add(objetCollectionEntitySansImage);
        objetCollectionEntities.add(null);

        List<ObjetCollectionDTO> res = objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionEntities);
        res.sort(Comparator.comparing(ObjetCollectionDTO::getId));

        assertSame(2, res.size());
        assertSame(objetCollectionEntityAvecImage.getId(), res.get(0).getId());
        assertSame(objetCollectionEntitySansImage.getId(), res.get(1).getId());
        assertEquals(objetCollectionEntityAvecImage.getCode(), res.get(0).getCode());
        assertEquals(objetCollectionEntitySansImage.getCode(), res.get(1).getCode());
        assertEquals(RareteEnum.get(objetCollectionEntityAvecImage.getRarete()), res.get(0).getRarete());
        assertEquals(RareteEnum.get(objetCollectionEntitySansImage.getRarete()), res.get(1).getRarete());
        assertSame(objetCollectionEntityAvecImage.getImage().getId(), res.get(0).getImage().getId());
        assertEquals(objetCollectionEntityAvecImage.getImage().getDescription(), res.get(0).getImage().getDescription());
        assertEquals(objetCollectionEntityAvecImage.getImage().getImage(), res.get(0).getImage().getImage());
        assertNull(res.get(1).getImage().getId());
        assertNull(res.get(1).getImage().getDescription());
        assertNull(res.get(1).getImage().getImage());
    }

    private ObjetCollectionEntity creerObjetCollection(Long id, String code, String rarete, ImageEntity image) {
        ObjetCollectionEntity objetCollection = new ObjetCollectionEntity();
        objetCollection.setId(1L);
        objetCollection.setCode("Aladdin");
        objetCollection.setRarete("C");
        objetCollection.setImage(image);
        return objetCollection;
    }

    private ImageEntity creerImage() {
        ImageEntity image = new ImageEntity();
        image.setId(3L);
        image.setDescription("Aladdin");
        image.setImage("Aladdin.png");
        return image;
    }
}
