-- Composant--
CREATE TABLE type_composant(
   id_type_composant SERIAL,
   nom_type_composant VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_composant)
);

CREATE TABLE composant(
   id_composant SERIAL,
   nom_composant VARCHAR(250)  NOT NULL,
   capacite NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(18,2)   NOT NULL,
   id_type_composant INTEGER NOT NULL,
   PRIMARY KEY(id_composant),
   UNIQUE(nom_composant),
   CHECK(capacite >= 0),
   CHECK(prix_unitaire >= 0),
   FOREIGN KEY(id_type_composant) REFERENCES type_composant(id_type_composant)
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

CREATE TABLE carte_mere_utilisation(
   id_composant INTEGER,
   id_carte_mere INTEGER,
   type_slot VARCHAR(20)  NOT NULL,
   date_installation VARCHAR(50)  DEFAULT NOW( ) ,
   PRIMARY KEY(id_composant, id_carte_mere),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant),
   FOREIGN KEY(id_carte_mere) REFERENCES carte_mere(id_carte_mere)
);

-- stock --
CREATE TABLE mouvement_stock(
   id_mouvement_stock SERIAL,
   date_mouvement DATE DEFAULT NOW( ),
   quantite_composant NUMERIC(15,2)   NOT NULL,
   est_entree BOOLEAN DEFAULT true,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_mouvement_stock),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

-- ordinateur
CREATE TABLE client(
   id_client VARCHAR(50) ,
   nom_client VARCHAR(250)  NOT NULL,
   PRIMARY KEY(id_client)
);

CREATE TABLE type_ordinateur(
   id_type_ordinateur SERIAL,
   nom_type_ordinateur VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_ordinateur)
);

CREATE TABLE ordinateur(
   id_ordinateur SERIAL,
   nom_ordinateur VARCHAR(150)  NOT NULL,
   description TEXT,
   id_client VARCHAR(50)  NOT NULL,
   id_type_ordinateur INTEGER NOT NULL,
   PRIMARY KEY(id_ordinateur),
   FOREIGN KEY(id_client) REFERENCES client(id_client),
   FOREIGN KEY(id_type_ordinateur) REFERENCES type_ordinateur(id_type_ordinateur)
);

CREATE TABLE composant_ordinateur(
   id_composant_ordinateur SERIAL,
   quantite SMALLINT DEFAULT 1,
   id_ordinateur INTEGER NOT NULL,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_composant_ordinateur),
   FOREIGN KEY(id_ordinateur) REFERENCES ordinateur(id_ordinateur),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

-- REPARATION
-- liste reparation par type composant
CREATE TABLE reparation(
   id_reparation SERIAL,
   id_ordinateur INTEGER NOT NULL,
   date_reparation DATE DEFAULT NOW(),
   PRIMARY KEY(id_reparation),
   FOREIGN KEY(id_ordinateur) REFERENCES ordinateur(id_ordinateur)
);

CREATE TABLE composant_reparation(
   id_reparation INTEGER,
   id_composant_ordinateur INTEGER,
   cout_reparation NUMERIC(18,2)   DEFAULT 0,
   PRIMARY KEY(id_reparation, id_composant_ordinateur),
   FOREIGN KEY(id_reparation) REFERENCES reparation(id_reparation),
   FOREIGN KEY(id_composant_ordinateur) REFERENCES composant_ordinateur(id_composant_ordinateur)
);