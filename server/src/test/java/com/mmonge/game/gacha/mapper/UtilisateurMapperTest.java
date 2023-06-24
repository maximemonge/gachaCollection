package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
public class UtilisateurMapperTest {

    private UtilisateurMapper utilisateurMapper;

    @BeforeEach
    public void beforeAll() {
        utilisateurMapper = new UtilisateurMapper();
    }

    @Test
    public void test_utilisateurEntityToDto() {
        UtilisateurEntity entity = new UtilisateurEntity();
        entity.setId(1L);
        entity.setIdentifiant("toto");
        entity.setMonnaie(12L);

        UtilisateurDTO dto = utilisateurMapper.utilisateurEntityToDto(entity);

        assertSame(1L, dto.getId());
        assertEquals("toto", dto.getIdentifiant());
        assertSame(12L, dto.getMonnaie());
    }
}
