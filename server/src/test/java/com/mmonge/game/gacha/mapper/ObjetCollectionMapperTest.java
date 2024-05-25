package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ObjetCollectionMapperTest {

    @Spy
    private ImageMapper imageMapper;
    private ObjetCollectionMapper objetCollectionMapper;

    @BeforeEach
    public void beforeEach() {
        objetCollectionMapper = new ObjetCollectionMapper(imageMapper);
    }

    @Test
    public void test_objetCollectionEntityToDtos_ok() {
        // Given
        ObjetCollectionEntity objetCollectionEntityA = creerObjetCollection(1L, "A", "C", creerImage(1L, "A", "A.png"));
        ObjetCollectionEntity objetCollectionEntityB = creerObjetCollection(2L, "B", "R", creerImage(2L, "B", "B.png"));
        List<ObjetCollectionEntity> objetCollectionEntities = Arrays.asList(objetCollectionEntityA, objetCollectionEntityB);

        // When
        List<ObjetCollectionDTO> res = objetCollectionMapper.objetCollectionEntityToDtos(objetCollectionEntities);

        // Then
        res.sort(Comparator.comparing(ObjetCollectionDTO::id));
        assertSame(2, res.size());
        assertSame(objetCollectionEntityA.getId(), res.get(0).id());
        assertEquals(objetCollectionEntityA.getCode(), res.get(0).code());
        assertEquals(RareteEnum.get(objetCollectionEntityA.getRarete()), res.get(0).rarete());
        assertSame(objetCollectionEntityA.getImage().getId(), res.get(0).image().id());
        assertEquals(objetCollectionEntityA.getImage().getDescription(), res.get(0).image().description());
        assertEquals(objetCollectionEntityA.getImage().getImage(), res.get(0).image().image());

        assertSame(objetCollectionEntityB.getId(), res.get(1).id());
        assertEquals(objetCollectionEntityB.getCode(), res.get(1).code());
        assertEquals(RareteEnum.get(objetCollectionEntityB.getRarete()), res.get(1).rarete());
        assertSame(objetCollectionEntityB.getImage().getId(), res.get(1).image().id());
        assertEquals(objetCollectionEntityB.getImage().getDescription(), res.get(1).image().description());
        assertEquals(objetCollectionEntityB.getImage().getImage(), res.get(1).image().image());
    }

    @Test
    public void test_objetCollectionEntityToDtos_null() {
        // Given
        ObjetCollectionEntity objetAvecImageNull = creerObjetCollection(1L, "A", "C", null);

        // When Then
        assertThrows(NullPointerException.class, () -> objetCollectionMapper.objetCollectionEntityToDtos(List.of(objetAvecImageNull)));
        assertThrows(NullPointerException.class, () -> objetCollectionMapper.objetCollectionEntityToDtos(Arrays.asList(null, null)));
    }

    private ObjetCollectionEntity creerObjetCollection(Long id, String code, String rarete, ImageEntity image) {
        ObjetCollectionEntity objetCollection = new ObjetCollectionEntity();
        objetCollection.setId(id);
        objetCollection.setCode(code);
        objetCollection.setRarete(rarete);
        objetCollection.setImage(image);
        return objetCollection;
    }

    private ImageEntity creerImage(Long id, String description, String img) {
        ImageEntity image = new ImageEntity();
        image.setId(id);
        image.setDescription(description);
        image.setImage(img);
        return image;
    }
}
