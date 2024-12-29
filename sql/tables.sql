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

CREATE TABLE processeur(
   id_processeur SERIAL,
   generation SMALLINT NOT NULL,
   nombre_coeur SMALLINT NOT NULL,
   id_type_processeur INTEGER,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_processeur),
   FOREIGN KEY(id_type_processeur) REFERENCES type_processeur(id_type_processeur),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

CREATE TABLE type_disque(
   id_type_disque SERIAL,
   nom_type_disque VARCHAR(10)  NOT NULL,
   PRIMARY KEY(id_type_disque)
);

CREATE TABLE disque_dur(
   id_disque_dur SERIAL,
   est_portable BOOLEAN DEFAULT false,
   id_type_disque INTEGER NOT NULL,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_disque_dur),
   FOREIGN KEY(id_type_disque) REFERENCES type_disque(id_type_disque),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

CREATE TABLE carte_mere(
   id_carte_mere SERIAL,
   nombre_slot_ram SMALLINT NOT NULL,
   nombre_slot_disque SMALLINT NOT NULL,
   id_type_disque INTEGER NOT NULL,
   id_type_processeur INTEGER NOT NULL,
   id_type_ram INTEGER NOT NULL,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_carte_mere),
   FOREIGN KEY(id_type_disque) REFERENCES type_disque(id_type_disque),
   FOREIGN KEY(id_type_processeur) REFERENCES type_processeur(id_type_processeur),
   FOREIGN KEY(id_type_ram) REFERENCES type_ram(id_type_ram),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);