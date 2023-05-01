package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;
import com.mmonge.game.gacha.model.dto.UtilisateurDTO;

public interface UtilisateurService {

    /**
     * Connecte un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return UtilisateurDTO
     */
    UtilisateurDTO login(String identifiant, String motDePasse) throws SecurityException;

    /**
     * Crée un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return UtilisateurDTO
     */
    UtilisateurDTO creerUtilisateur(String identifiant, String motDePasse) throws DuplicationDonneeException;
}
