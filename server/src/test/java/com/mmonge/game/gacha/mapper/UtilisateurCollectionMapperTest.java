package com.mmonge.game.gacha.mapper;

import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.model.entity.ObjetCollectionEntity;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UtilisateurCollectionMapperTest {

    private UtilisateurCollectionMapper utilisateurCollectionMapper;

    @BeforeEach
    public void beforeAll() {
        utilisateurCollectionMapper = new UtilisateurCollectionMapper();
    }

    @Test
    public void test_utilisateurCollectionEntityToDtos_vide() {
        assertTrue(CollectionUtils.isEmpty(utilisateurCollectionMapper.utilisateurCollectionEntityToDtos(null)));
    }

    @Test
    public void test_utilisateurCollectionEntityToDtos_ok() {
        List<UtilisateurCollectionEntity> entities = Arrays.asList(creerUtilisateurCollection(1L, 2L), creerUtilisateurCollection(3L, 4L));

        List<UtilisateurCollectionDTO> res = utilisateurCollectionMapper.utilisateurCollectionEntityToDtos(entities);
        res.sort(Comparator.comparing(UtilisateurCollectionDTO::getObjetCollectionId));

        assertSame(1L, res.get(0).getObjetCollectionId());
        assertSame(2L, res.get(0).getQuantite());
        assertSame(3L, res.get(1).getObjetCollectionId());
        assertSame(4L, res.get(1).getQuantite());
    }

    private UtilisateurCollectionEntity creerUtilisateurCollection(Long objetId, Long quantite) {
        UtilisateurCollectionEntity entity = new UtilisateurCollectionEntity();
        ObjetCollectionEntity objet = new ObjetCollectionEntity();
        objet.setId(objetId);
        entity.setQuantite(quantite);
        entity.setObjetCollection(objet);
        return entity;
    }
}
