package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.mapper.UtilisateurMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.services.repository.UtilisateurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource("/application.properties")
@Sql("classpath:h2config/utilisateur_service_test.sql")
public class UtilisateurServiceTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private UtilisateurService utilisateurService;

    @BeforeEach
    public void beforeAll() {
        utilisateurService = new UtilisateurService(utilisateurRepository, new UtilisateurMapper());
    }

    @Test
    public void test_login() {
        UtilisateurDTO res = utilisateurService.login("mmonge", "mmonge");

        assertNotNull(res.getId());
        assertEquals("mmonge", res.getIdentifiant());
        assertSame(100L, res.getMonnaie());
    }

    @Test
    public void test_login_mauvaisMdp() {
        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login("mmonge", "motDePasseIncorrect")
        );
    }

    @Test
    public void test_login_aucunUtilisateur() {
        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login("utilisateurInexistant", "")
        );
    }

    @Test
    public void test_login_null() {
        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login(null, null)
        );
    }

    @Test
    public void test_creerUtilisateur_identifiantUtilise() {
        assertThrows(
                DuplicationDonneeException.class,
                () -> utilisateurService.creerUtilisateur("mmonge", "mmonge")
        );
    }

    @Test
    public void test_creerUtilisateur_identifiantPasUtilise() throws DuplicationDonneeException {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        UtilisateurDTO res = utilisateurService.creerUtilisateur(identifiant, motDePasse);

        assertNotNull(res.getId());
        assertEquals(identifiant, res.getIdentifiant());
        assertSame(10L, res.getMonnaie());
    }
}