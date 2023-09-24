package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.mapper.UtilisateurMapper;
import com.mmonge.game.gacha.model.dto.UtilisateurDTO;
import com.mmonge.game.gacha.model.entity.UtilisateurEntity;
import com.mmonge.game.gacha.services.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    private final static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Connecte un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return UtilisateurDTO
     */
    public UtilisateurDTO login(String identifiant, String motDePasse) throws SecurityException {
        UtilisateurEntity utilisateur = utilisateurRepository.getByIdentifiant(identifiant);
        if (utilisateur != null && passwordEncoder.matches(motDePasse, utilisateur.getMotDePasse())) {
            return utilisateurMapper.utilisateurEntityToDto(utilisateur);
        } else {
            throw new SecurityException();
        }
    }

    /**
     * Crée un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return UtilisateurDTO
     */
    public UtilisateurDTO creerUtilisateur(String identifiant, String motDePasse) throws DuplicationDonneeException {
        if (utilisateurRepository.isIdentifiantUtilise(identifiant)) {
            throw new DuplicationDonneeException();
        } else {
            Date now = new Date();
            UtilisateurEntity utilisateur = new UtilisateurEntity();
            utilisateur.setIdentifiant(identifiant);
            utilisateur.setMotDePasse(passwordEncoder.encode(motDePasse));
            utilisateur.setDateCreation(now);
            utilisateur.setDateModification(now);
            utilisateur.setMonnaie(10L);
            return utilisateurMapper.utilisateurEntityToDto(utilisateurRepository.save(utilisateur));
        }
    }

    public void retirerMonnaie(Long utilisateurId, Long monnaie) {
        utilisateurRepository.ajouterMonnaie(utilisateurId, -monnaie);
    }

    public UtilisateurDTO ajouterMonnaie(Long utilisateurId, Long monnaie) {
        utilisateurRepository.ajouterMonnaie(utilisateurId, monnaie);
        return utilisateurMapper.utilisateurEntityToDto(utilisateurRepository.findById(utilisateurId).orElse(null));
    }
}