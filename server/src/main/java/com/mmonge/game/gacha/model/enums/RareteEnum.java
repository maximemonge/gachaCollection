package com.mmonge.game.gacha.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@AllArgsConstructor
public enum RareteEnum {
    C("C", "Commun", 50),
    S("S", "Peu commun", 80),
    R("R", "Rare", 90),
    E("E", "Épique", 96),
    L("L", "Légendaire", 99),
    M("M", "Mythique", 100);

    private final String code;
    private final String libelle;
    private final int chance;
    private static final Logger LOGGER = LoggerFactory.getLogger(RareteEnum.class);

    public static RareteEnum get(String code) {
        RareteEnum rarete = null;
        try {
            rarete = RareteEnum.valueOf(code);
        } catch (Exception e) {
            LOGGER.warn("Impossible de convertir le code {} en RareteEnum : {}", code, e.getMessage(), e);
        }
        return rarete;
    }
}
