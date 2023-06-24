package com.mmonge.game.gacha.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmonge.game.gacha.model.pojo.UtilisateurPojo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource("/application.properties")
@Sql("classpath:h2config/utilisateur_service_test.sql")
@Transactional
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_login_ok() throws Exception {
        mockMvc.perform(post("/user/login")
                        .content(new ObjectMapper().writeValueAsString(creerLoginInfos("mmonge", "mmonge")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.identifiant").value("mmonge"))
                .andExpect(jsonPath("$.monnaie").value(100));
    }

    @Test
    public void test_login_ko() throws Exception {
        mockMvc.perform(post("/user/login")
                        .content(new ObjectMapper().writeValueAsString(creerLoginInfos("mmonge", "mauvaisMdp")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }

    @Test
    public void test_creerUtilisateur_ok() throws Exception {
        mockMvc.perform(post("/user/new")
                        .content(new ObjectMapper().writeValueAsString(creerLoginInfos("toto", "titi")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.identifiant").value("toto"))
                .andExpect(jsonPath("$.monnaie").value(10));
    }

    @Test
    public void test_creerUtilisateur_loginDejaUtilise() throws Exception {
        mockMvc.perform(post("/user/new")
                        .content(new ObjectMapper().writeValueAsString(creerLoginInfos("mmonge", "mdpDifferent")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    private UtilisateurPojo creerLoginInfos(String id, String mdp) {
        UtilisateurPojo loginInfos = new UtilisateurPojo();
        loginInfos.setIdentifiant(id);
        loginInfos.setMotDePasse(mdp);
        return loginInfos;
    }
}
