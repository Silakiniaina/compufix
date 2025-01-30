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
   id_type_composant INTEGER NOT NULL,
   PRIMARY KEY(id_composant),
   UNIQUE(nom_composant),
   CHECK(capacite >= 0),
   FOREIGN KEY(id_type_composant) REFERENCES type_composant(id_type_composant)
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
CREATE TABLE type_reparation(
   id_type_reparation SERIAL,
   nom_type_reparation VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_reparation)
);

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
   id_type_reparation INTEGER,
   cout_reparation NUMERIC(18,2)   DEFAULT 0,
   PRIMARY KEY(id_reparation, id_composant_ordinateur, id_type_reparation),
   FOREIGN KEY(id_reparation) REFERENCES reparation(id_reparation),
   FOREIGN KEY(id_composant_ordinateur) REFERENCES composant_ordinateur(id_composant_ordinateur),
   FOREIGN KEY(id_type_reparation) REFERENCES type_reparation(id_type_reparation)
);

CREATE TABLE retour(
   id_retour SERIAL,
   date_retour DATE DEFAULT NOW( ),
   id_reparation INTEGER NOT NULL,
   PRIMARY KEY(id_retour),
   FOREIGN KEY(id_reparation) REFERENCES reparation(id_reparation)
);

CREATE TABLE historique_prix_composant(
   id_historique SERIAL,
   date_modification DATE DEFAULT NOW() ,
   ancien_prix NUMERIC(18,2)   NOT NULL CHECK(ancien_prix >= 0),
   nouveau_prix NUMERIC(18,2)   NOT NULL CHECK(nouveau_prix >= 0),
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_historique),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);