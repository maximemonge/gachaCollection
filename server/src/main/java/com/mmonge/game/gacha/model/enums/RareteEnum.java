package com.mmonge.game.gacha.model.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public enum RareteEnum implements Comparator<RareteEnum> {
    C(1, "C", "Commun", 50000),
    S(2, "S", "Peu commun", 80000),
    R(3, "R", "Rare", 90000),
    E(4, "E", "Épique", 96000),
    L(5, "L", "Légendaire", 99000),
    M(6, "M", "Mythique", 99999),
    F(7, "F", "Fantôme", 100000);
    
    private final int ordre;
    private final String code;
    private final String libelle;
    private final int chance;
    private static final Logger LOGGER = LoggerFactory.getLogger(RareteEnum.class);

    RareteEnum(int ordre, String code, String libelle, int chance) {
        this.ordre = ordre;
        this.code = code;
        this.libelle = libelle;
        this.chance = chance;
    }

    public static RareteEnum get(String code) {
        RareteEnum rarete = null;
        try {
            rarete = RareteEnum.valueOf(code);
        } catch (Exception e) {
            LOGGER.warn("Impossible de convertir le code {} en RareteEnum : {}", code, e.getMessage(), e);
        }
        return rarete;
    }

    @Override
    public int compare(RareteEnum o1, RareteEnum o2) {
        return Integer.compare(o1.ordre, o2.ordre);
    }

    public int getOrdre() {
        return ordre;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getChance() {
        return chance;
    }
}
