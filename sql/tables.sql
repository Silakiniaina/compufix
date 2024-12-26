CREATE TABLE composant(
   id_composant SERIAL,
   nom_composant VARCHAR(250)  NOT NULL,
   capacite NUMERIC(15,2) NOT NULL,
   prix_unitaire NUMERIC(18,2)   NOT NULL,
   PRIMARY KEY(id_composant),
   UNIQUE(nom_composant),
   CHECK(capacite >= 0),
   CHECK(prix_unitaire >= 0)
);

CREATE TABLE type_ram(
   id_type_ram SERIAL,
   nom_type_ram VARCHAR(20)  NOT NULL,
   PRIMARY KEY(id_type_ram),
   UNIQUE(nom_type_ram)
);
