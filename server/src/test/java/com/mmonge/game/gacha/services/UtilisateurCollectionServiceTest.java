package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.mapper.UtilisateurCollectionMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurCollectionDTO;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
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
@Sql("classpath:h2config/utilisateur_collection_service_test.sql")
public class UtilisateurCollectionServiceTest {

    @Autowired
    private UtilisateurCollectionRepository utilisateurCollectionRepository;
    private UtilisateurCollectionService utilisateurCollectionService;

    @BeforeEach
    public void beforeAll() {
        utilisateurCollectionService = new UtilisateurCollectionService(utilisateurCollectionRepository, new UtilisateurCollectionMapper());
    }

    @Test
    public void test_findAllIdsByUtilisateurId() {
        List<UtilisateurCollectionDTO> res = utilisateurCollectionService.findAllIdsByUtilisateurId(1L);

        assertSame(2, res.size());
        assertTrue(res.stream().map(UtilisateurCollectionDTO::getObjetCollectionId).toList().containsAll(Arrays.asList(1L, 3L)));
        assertTrue(res.stream().map(UtilisateurCollectionDTO::getQuantite).toList().contains(1L));
    }

    @Test
    public void test_findAllIdsByUtilisateurId_aucunResultat() {
        List<UtilisateurCollectionDTO> res = utilisateurCollectionService.findAllIdsByUtilisateurId(2L);

        assertSame(0, res.size());
    }

    @Test
    public void test_findAllIdsByUtilisateurId_utilisateurInexistant() {
        List<UtilisateurCollectionDTO> res = utilisateurCollectionService.findAllIdsByUtilisateurId(3L);

        assertSame(0, res.size());
    }
}