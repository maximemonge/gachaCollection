package com.mmonge.game.gacha.model.enums;

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

    RareteEnum(String code, String libelle, int pourcentage) {
        this.code = code;
        this.libelle = libelle;
        this.pourcentage = pourcentage;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public int getPourcentage() {
        return pourcentage;
    }
}
