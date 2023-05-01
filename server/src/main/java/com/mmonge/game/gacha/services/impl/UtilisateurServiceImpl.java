package com.mmonge.game.gacha.services.impl;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import com.mmonge.game.gacha.services.UtilisateurService;
import com.mmonge.game.gacha.services.impl.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;

    private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public Long login(String identifiant, String motDePasse) throws SecurityException {
        try {
            UtilisateurEntity utilisateur = utilisateurRepository.getByIdentifiant(identifiant);
            if (passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
                return utilisateur.getId();
            } else {
                throw new SecurityException();
            }
        } catch (Exception e) {
            throw new SecurityException();
        }
    }

    @Override
    public Long creerUtilisateur(String identifiant, String motDePasse) throws DuplicationDonneeException {
        if (utilisateurRepository.isIdentifiantUtilise(identifiant)) {
            throw new DuplicationDonneeException();
        } else {
            Date now = new Date();
            UtilisateurEntity utilisateur = new UtilisateurEntity();
            utilisateur.setIdentifiant(identifiant);
            utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse));
            utilisateur.setDateCreation(now);
            utilisateur.setDateModification(now);
            return utilisateurRepository.save(utilisateur).getId();
        }
    }

    @Autowired
    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }
}