package com.mmonge.game.gacha.web.controller;

import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import com.mmonge.game.gacha.services.repository.UtilisateurCollectionRepository;
import com.mmonge.game.gacha.services.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application.properties")
@Sql("classpath:h2config/objet_collection_service_test.sql")
@Transactional
public class ObjetCollectionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private UtilisateurCollectionRepository utilisateurCollectionRepository;

    @Test
    public void test_getAllObjetCollection_ok() throws Exception {
        mockMvc.perform(get("/objetCollection/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Aladdin", hasSize(2)))
                .andExpect(jsonPath("$.Aladdin[0].code").value("Aladdin"))
                .andExpect(jsonPath("$.Aladdin[1].code").value("Jafar"))
                .andExpect(jsonPath("$.['La petite sirène']", hasSize(1)))
                .andExpect(jsonPath("$.['La petite sirène'][0].code").value("Ariel"));
    }

    @Test
    public void test_getAllObjetCollectionByUtilisateurId_ok() throws Exception {
        mockMvc.perform(get("/objetCollection/all/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void test_getAllObjetCollectionByUtilisateurId_rien() throws Exception {
        mockMvc.perform(get("/objetCollection/all/user/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void test_obtenirUnObjet() throws Exception {
        mockMvc.perform(get("/objetCollection/obtenir/user/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

        // on vérifie que l'utilisateur a bien un objet de plus dans sa collection, et qu'il a perdu 1 de monnaie
        mockMvc.perform(get("/objetCollection/all/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        Optional<UtilisateurEntity> utilisateur = utilisateurRepository.findById(1L);
        assertSame(99L, utilisateur.map(UtilisateurEntity::getMonnaie).orElse(null));
    }

    @Test
    public void test_getOneObjetCollectionAleatoire() throws Exception {
        mockMvc.perform(get("/objetCollection/random")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }
}
