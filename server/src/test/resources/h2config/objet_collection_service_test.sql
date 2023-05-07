insert into IMAGE (img_description, img_image) values ('Aladdin', 'aladdin.png');
insert into IMAGE (img_description, img_image) values ('Ariel', 'ariel.png');
insert into IMAGE (img_description, img_image) values ('Jafar', 'jafar.png');

insert into OBJET_A_COLLECTIONNER (oac_id, oac_code, oac_rarete, oac_img_id, oac_categorie) values (1, 'Aladdin', 'C', (select img_id from IMAGE where img_description = 'Aladdin'), 'Aladdin');
insert into OBJET_A_COLLECTIONNER (oac_id, oac_code, oac_rarete, oac_img_id, oac_categorie) values (2, 'Ariel', 'R', (select img_id from IMAGE where img_description = 'Ariel'), 'La petite sirène');
insert into OBJET_A_COLLECTIONNER (oac_id, oac_code, oac_rarete, oac_img_id, oac_categorie) values (3, 'Jafar', 'M', (select img_id from IMAGE where img_description = 'Jafar'), 'Aladdin');

insert into UTILISATEUR (usr_id, usr_identifiant, usr_mot_de_passe, usr_date_creation, usr_date_modification) values (1, 'mmonge', '{bcrypt}$2a$10$8xQXCsc/12uJea/9b9.fBezUXOqJA.wgTxOu0XeRkdj4SKf3u8u4C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into UTILISATEUR (usr_id, usr_identifiant, usr_mot_de_passe, usr_date_creation, usr_date_modification) values (2, 'amonge', '{bcrypt}$2a$10$txl4idpuRfwnkpemqHtb1.MBakPbXEYE.cwnGtLFh3kX.XvKcusS.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into UTILISATEUR_COLLECTION (uco_usr_id, uco_oac_id) values (1, 1);
insert into UTILISATEUR_COLLECTION (uco_usr_id, uco_oac_id) values (1, 3);