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
    public void beforeEach() {
        imageMapper = new ImageMapper();
    }

    @Test
    public void test_imageEntityToDto_ok() {
        // Given
        ImageEntity entity = new ImageEntity();
        entity.setId(1L);
        entity.setDescription("Ariel");
        entity.setImage("Ariel.png");

        // When
        ImageDTO dto = imageMapper.imageEntityToDto(entity);

        // Then
        assertSame(entity.getId(), dto.id());
        assertEquals(entity.getDescription(), dto.description());
        assertEquals(entity.getImage(), dto.image());
    }

    @Test
    public void test_imageEntityToDto_null() {
        assertThrows(NullPointerException.class, () -> imageMapper.imageEntityToDto(null));
    }
}
