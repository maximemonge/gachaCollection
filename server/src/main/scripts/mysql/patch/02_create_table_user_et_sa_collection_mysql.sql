CREATE TABLE IF NOT EXISTS UTILISATEUR (
    usr_id BIGINT AUTO_INCREMENT NOT NULL,
    usr_identifiant VARCHAR(50) NOT NULL,
    usr_mot_de_passe VARCHAR(512) NOT NULL,
    usr_date_creation DATETIME NOT NULL,
    usr_date_modification DATETIME NOT NULL,
    usr_date_suppression DATETIME,
    PRIMARY KEY (usr_id)
);

CREATE TABLE IF NOT EXISTS UTILISATEUR_COLLECTION (
    uco_usr_id BIGINT NOT NULL,
    uco_oac_id BIGINT NOT NULL,
    uco_quantite BIGINT default 1 NOT NULL,
    PRIMARY KEY (uco_usr_id, uco_oac_id),
    CONSTRAINT fk_uco_usr_id FOREIGN KEY (uco_usr_id) REFERENCES UTILISATEUR(usr_id) ON DELETE CASCADE,
    CONSTRAINT fk_uco_oac_id FOREIGN KEY (uco_oac_id) REFERENCES OBJET_A_COLLECTIONNER(oac_id) ON DELETE CASCADE
);