drop table UTILISATEUR_COLLECTION;
drop table UTILISATEUR;
drop table OBJET_A_COLLECTIONNER;
drop table IMAGE;

CREATE TABLE IF NOT EXISTS IMAGE (
    img_id BIGINT AUTO_INCREMENT NOT NULL,
    img_description VARCHAR(100),
    img_image BLOB NOT NULL,
    PRIMARY KEY (img_id)
);

CREATE TABLE IF NOT EXISTS OBJET_A_COLLECTIONNER (
    oac_id BIGINT AUTO_INCREMENT NOT NULL,
    oac_code VARCHAR(100) NOT NULL,
    oac_rarete VARCHAR(2) NOT NULL,
    oac_img_id BIGINT NOT NULL,
    oac_categorie VARCHAR(50) NOT NULL,
    PRIMARY KEY (oac_id),
    CONSTRAINT fk_oac_img_id FOREIGN KEY (oac_img_id) REFERENCES IMAGE(img_id) ON DELETE CASCADE
);

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

DROP PROCEDURE IF EXISTS add_column_if_not_exists;

DELIMITER $$
$$

CREATE PROCEDURE add_column_if_not_exists(in nom_table VARCHAR(30), in nom_colonne VARCHAR(30), in type_colonne VARCHAR(50))
begin
    if ((select count(nom_colonne) from INFORMATION_SCHEMA.columns where TABLE_SCHEMA = DATABASE() and TABLE_NAME = nom_table and COLUMN_NAME = nom_colonne) = 0)
    then
        SET @query = CONCAT('alter table ', nom_table);
        SET @query = CONCAT(@query, ' ', 'add column') ;
        SET @query = CONCAT(@query, ' ', nom_colonne);
        SET @query = CONCAT(@query, ' ', type_colonne);

        PREPARE proc FROM @query;
        EXECUTE proc;
        DEALLOCATE PREPARE proc;

    end if;
end$$
DELIMITER ;

call add_column_if_not_exists('utilisateur', 'usr_monnaie',  'BIGINT not null');
alter table utilisateur add constraint ck_usr_monnaie check (usr_monnaie > -1);