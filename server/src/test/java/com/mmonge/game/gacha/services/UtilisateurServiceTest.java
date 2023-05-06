package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.mapper.UtilisateurMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import com.mmonge.game.gacha.services.repository.UtilisateurRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest {

    @Mock
    private UtilisateurRepository utilisateurRepository;
    @Spy
    private UtilisateurMapper utilisateurMapper;

    private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private UtilisateurService utilisateurService;

    @BeforeEach
    public void beforeAll() {
        utilisateurService = new UtilisateurService(utilisateurRepository, utilisateurMapper);
    }

    @Test
    public void test_login() {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        UtilisateurEntity utilisateur = creerUtilisateur(1L, identifiant, motDePasse);
        Mockito.when(utilisateurRepository.getByIdentifiant(identifiant)).thenReturn(utilisateur);

        UtilisateurDTO res = utilisateurService.login(identifiant, motDePasse);

        assertSame(1L, res.getId());
        assertEquals(identifiant, res.getIdentifiant());
    }

    @Test
    public void test_login_mauvaisMdp() {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        UtilisateurEntity utilisateur = creerUtilisateur(2L, identifiant, motDePasse + "oups");
        Mockito.when(utilisateurRepository.getByIdentifiant(identifiant)).thenReturn(utilisateur);

        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login(identifiant, motDePasse)
        );
    }

    @Test
    public void test_login_aucunUtilisateur() {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        Mockito.when(utilisateurRepository.getByIdentifiant(identifiant)).thenReturn(null);

        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login(identifiant, motDePasse)
        );
    }

    @Test
    public void test_login_exceptionRechercheUtilisateur() {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        Mockito.when(utilisateurRepository.getByIdentifiant(identifiant)).thenThrow(NoResultException.class);

        assertThrows(
                SecurityException.class,
                () -> utilisateurService.login(identifiant, motDePasse)
        );
    }

    @Test
    public void test_creerUtilisateur_identifiantUtilise() {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        Mockito.when(utilisateurRepository.isIdentifiantUtilise(identifiant)).thenReturn(true);

        assertThrows(
                DuplicationDonneeException.class,
                () -> utilisateurService.creerUtilisateur(identifiant, motDePasse)
        );
        Mockito.verify(utilisateurRepository, Mockito.times(0)).save(Mockito.any());
    }

    @Test
    public void test_creerUtilisateur_identifiantPasUtilise() throws DuplicationDonneeException {
        String identifiant = "Toto";
        String motDePasse = "TotoMdp";
        Mockito.when(utilisateurRepository.isIdentifiantUtilise(identifiant)).thenReturn(false);
        Mockito.when(utilisateurRepository.save(Mockito.any(UtilisateurEntity.class))).thenReturn(creerUtilisateur(3L, identifiant, motDePasse));

        UtilisateurDTO res = utilisateurService.creerUtilisateur(identifiant, motDePasse);

        assertSame(3L, res.getId());
        assertEquals(identifiant, res.getIdentifiant());
        Mockito.verify(utilisateurRepository, Mockito.times(1)).save(Mockito.any());
    }

    private UtilisateurEntity creerUtilisateur(Long id, String identifiant, String motDePasse) {
        UtilisateurEntity utilisateur = new UtilisateurEntity();
        utilisateur.setId(id);
        utilisateur.setIdentifiant(identifiant);
        utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse));
        return utilisateur;
    }
}