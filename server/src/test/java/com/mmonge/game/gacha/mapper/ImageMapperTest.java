package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.ImageDTO;
import com.mmonge.game.gacha.model.entity.ImageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ImageMapperTest {

    private ImageMapper imageMapper;

    @BeforeEach
    public void beforeAll() {
        imageMapper = new ImageMapper();
    }

    @Test
    public void test_imageEntityToDto_ok() {
        ImageEntity entity = new ImageEntity();
        entity.setId(1L);
        entity.setDescription("Ariel");
        entity.setImage("Ariel.png");

        ImageDTO dto = imageMapper.imageEntityToDto(entity);

        assertSame(entity.getId(), dto.getId());
        assertEquals(entity.getDescription(), dto.getDescription());
        assertEquals(entity.getImage(), dto.getImage());
    }

    @Test
    public void test_imageEntityToDto_null() {
        ImageDTO dto = imageMapper.imageEntityToDto(null);

        assertNull(dto.getId());
        assertNull(dto.getDescription());
        assertNull(dto.getImage());
    }
}
