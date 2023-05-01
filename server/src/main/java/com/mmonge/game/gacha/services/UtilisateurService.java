package com.mmonge.game.gacha.services;

import com.mmonge.game.gacha.exception.DuplicationDonneeException;

public interface UtilisateurService {

    /**
     * Connecte un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return utilisateurId
     */
    Long login(String identifiant, String motDePasse) throws SecurityException;

    /**
     * Crée un utilisateur
     *
     * @param identifiant : identifiant de l'utilisateur
     * @param motDePasse  : mot de passe de l'utilisateur
     * @return utilisateurId
     */
    Long creerUtilisateur(String identifiant, String motDePasse) throws DuplicationDonneeException;
}
