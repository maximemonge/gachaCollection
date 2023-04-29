CREATE TABLE IF NOT EXISTS IMAGE (
    img_id BIGINT AUTO_INCREMENT,
    img_description VARCHAR(100),
    img_image BLOB,
    PRIMARY KEY (img_id)
);

CREATE TABLE IF NOT EXISTS OBJET_A_COLLECTIONNER (
    oac_id BIGINT AUTO_INCREMENT,
    oac_code VARCHAR(100),
    oac_rarete VARCHAR(2),
    oac_img_id BIGINT,
    PRIMARY KEY (oac_id),
    CONSTRAINT fk_objet_collection_image FOREIGN KEY (oac_img_id) REFERENCES IMAGE(img_id)
);