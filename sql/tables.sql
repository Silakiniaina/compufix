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

CREATE TABLE RAM(
   id_ram SERIAL,
   est_portable BOOLEAN DEFAULT true,
   id_type_ram INTEGER NOT NULL,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_ram),
   FOREIGN KEY(id_type_ram) REFERENCES type_ram(id_type_ram),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

CREATE TABLE type_processeur(
   id_type_processeur SERIAL,
   nom_type_processeur VARCHAR(10)  NOT NULL,
   PRIMARY KEY(id_type_processeur)
);