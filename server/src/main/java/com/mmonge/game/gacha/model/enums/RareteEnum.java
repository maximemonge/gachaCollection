package com.mmonge.game.gacha.model.enums;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public enum RareteEnum {
    C("C", "Commun", 50),
    S("S", "Peu commun", 30),
    R("R", "Rare", 10),
    E("E", "Épique", 6),
    L("L", "Légendaire", 3),
    M("M", "Mythique", 1);

    private final String code;
    private final String libelle;
    private final int pourcentage;

    private static final Logger LOGGER = LoggerFactory.getLogger(RareteEnum.class);

    RareteEnum(String code, String libelle, int pourcentage) {
        this.code = code;
        this.libelle = libelle;
        this.pourcentage = pourcentage;
    }

    public static RareteEnum get(String code) {
        RareteEnum rarete = null;
        try {
            rarete = RareteEnum.valueOf(code);
        }
        catch (Exception e) {
            LOGGER.warn("Impossible de convertir le code {} en RareteEnum : {}", code, e.getMessage(), e);
        }
        return rarete;
    }
}
