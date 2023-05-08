package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ImageMapper;
import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurCollectionEntity;
import com.mmonge.game.gacha.model.enums.RareteEnum;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import com.mmonge.game.gacha.utils.RareteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource("/application.properties")
@Sql("classpath:h2config/objet_collection_service_test.sql")
public class ObjetCollectionServiceTest {

    @Autowired
    private ObjetCollectionRepository objetCollectionRepository;
    @Autowired
    private UtilisateurCollectionRepository utilisateurCollectionRepository;

    @Mock
    RareteUtils rareteUtils;
    private ObjetCollectionService objetCollectionService;

    @BeforeEach
    public void beforeAll() {
        objetCollectionService = new ObjetCollectionService(objetCollectionRepository, utilisateurCollectionRepository, new ObjetCollectionMapper(new ImageMapper()), rareteUtils);
    }

    @Test
    public void test_findAll() {
        List<ObjetCollectionDTO> res = objetCollectionService.findAll();

        assertSame(3, res.size());
        assertTrue(res.stream().map(ObjetCollectionDTO::getCode).toList().containsAll(Arrays.asList("Aladdin", "Ariel", "Jafar")));
        assertTrue(res.stream().map(o -> o.getImage().getId()).toList().containsAll(Arrays.asList(1L, 2L, 3L)));
    }

    @Test
    public void test_obtenirUnObjet() {
        Mockito.when(rareteUtils.getRareteAleatoire()).thenReturn(RareteEnum.M);

        ObjetCollectionDTO res = objetCollectionService.obtenirUnObjet(1L);
        List<UtilisateurCollectionEntity> collection = utilisateurCollectionRepository.findAllByUtilisateurId(1L);

        assertEquals("Jafar", res.getCode());
        assertSame(1, collection.size());
        assertSame(res.getId(), collection.get(0).getObjetCollection().getId());
        assertSame(1L, collection.get(0).getUtilisateur().getId());
        assertSame(2L, collection.get(0).getQuantite());
    }
}