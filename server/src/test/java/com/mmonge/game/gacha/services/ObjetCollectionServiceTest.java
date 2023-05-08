package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.ImageMapper;
import com.mmonge.game.gacha.mapper.ObjetCollectionMapper;
import com.mmonge.game.gacha.model.dto.ObjetCollectionDTO;
import com.mmonge.game.gacha.services.repository.ObjetCollectionRepository;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import com.mmonge.game.gacha.utils.AleatoireUtils;
import com.mmonge.game.gacha.utils.RareteUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    private ObjetCollectionService objetCollectionService;

    @BeforeEach
    public void beforeAll() {
        objetCollectionService = new ObjetCollectionService(objetCollectionRepository, utilisateurCollectionRepository, new ObjetCollectionMapper(new ImageMapper()), new RareteUtils(new AleatoireUtils()));
    }

    @Test
    public void test_findAll() {
        List<ObjetCollectionDTO> res = objetCollectionService.findAll();

        assertSame(3, res.size());
        assertTrue(res.stream().map(ObjetCollectionDTO::getCode).toList().containsAll(Arrays.asList("Aladdin", "Ariel", "Jafar")));
        assertTrue(res.stream().map(o -> o.getImage().getId()).toList().containsAll(Arrays.asList(1L, 2L, 3L)));
    }

    @Test
    public void test_findAllIdsByUtilisateurId() {
        List<Long> res = objetCollectionService.findAllIdsByUtilisateurId(1L);

        assertSame(2, res.size());
        assertTrue(res.containsAll(Arrays.asList(1L, 3L)));
    }
}