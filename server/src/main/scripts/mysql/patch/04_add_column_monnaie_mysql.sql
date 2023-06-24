call add_column_if_not_exists('utilisateur', 'usr_monnaie',  'BIGINT not null');
alter table utilisateur add constraint ck_usr_monnaie check (usr_monnaie > -1);