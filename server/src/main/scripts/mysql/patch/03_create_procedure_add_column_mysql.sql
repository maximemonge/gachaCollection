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
