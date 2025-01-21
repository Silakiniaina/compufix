CREATE TABLE technicien(
   id_technicien SERIAL,
   nom_technicien VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_technicien)
);

CREATE TABLE type_composant(
   id_type_composant SERIAL,
   nom_type_composant VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_composant)
);

CREATE TABLE type_reparation(
   id_type_reparation SERIAL,
   nom_type_reparation VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_reparation)
);

CREATE TABLE client(
   id_client SERIAL,
   nom_client VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_client)
);

CREATE TABLE type_ordinateur(
   id_type_ordinateur SERIAL,
   nom_type_ordinateur VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_ordinateur)
);

CREATE TABLE composant(
   id_composant SERIAL,
   nom_composant VARCHAR(250)  NOT NULL,
   capacite NUMERIC(15,2)   NOT NULL,
   prix_unitaire NUMERIC(18,2)   NOT NULL,
   id_type_composant INTEGER NOT NULL,
   PRIMARY KEY(id_composant),
   UNIQUE(nom_composant),
   FOREIGN KEY(id_type_composant) REFERENCES type_composant(id_type_composant)
);

CREATE TABLE ordinateur(
   id_ordinateur SERIAL,
   nom_ordinateur VARCHAR(150)  NOT NULL,
   description TEXT,
   id_type_ordinateur INTEGER NOT NULL,
   PRIMARY KEY(id_ordinateur),
   FOREIGN KEY(id_type_ordinateur) REFERENCES type_ordinateur(id_type_ordinateur)
);

CREATE TABLE mouvement_stock(
   id_mouvement_stock SERIAL,
   date_mouvement DATE DEFAULT NOW( ),
   quantite_composant NUMERIC(15,2)   NOT NULL,
   est_entree BOOLEAN DEFAULT FALSE,
   id_composant INTEGER NOT NULL,
   PRIMARY KEY(id_mouvement_stock),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);

CREATE TABLE reparation(
    id_reparation SERIAL,
    date_reparation DATE DEFAULT NOW(),
    id_ordinateur INTEGER NOT NULL,
    PRIMARY KEY(id_reparation),
    FOREIGN KEY(id_ordinateur) REFERENCES ordinateur(id_ordinateur)
);

CREATE TABLE composant_ordinateur(
    id_composant_ordinateur SERIAL,
    id_composant INTEGER NOT NULL,
    id_ordinateur INTEGER NOT NULL,
    PRIMARY KEY(id_composant_ordinateur),
    FOREIGN KEY(id_composant) REFERENCES composant(id_composant),
    FOREIGN KEY(id_ordinateur) REFERENCES ordinateur(id_ordinateur)
);

CREATE TABLE retour_reparation(
   id_retour_reparation SERIAL,
   date_retour DATE DEFAULT NOW( ),
   prix_total NUMERIC(18,2)   CHECK(prix_total >=0),
   id_reparation INTEGER NOT NULL,
   PRIMARY KEY(id_retour_reparation),
   FOREIGN KEY(id_reparation) REFERENCES reparation(id_reparation)
);

CREATE TABLE composant_reparation(
   id_reparation INTEGER,
   id_technicien INTEGER,
   id_type_reparation INTEGER,
   id_composant_ordinateur INTEGER,
   PRIMARY KEY(id_reparation, id_technicien, id_type_reparation, id_composant_ordinateur),
   FOREIGN KEY(id_reparation) REFERENCES reparation(id_reparation),
   FOREIGN KEY(id_technicien) REFERENCES technicien(id_technicien),
   FOREIGN KEY(id_type_reparation) REFERENCES type_reparation(id_type_reparation),
   FOREIGN KEY(id_composant_ordinateur) REFERENCES composant_ordinateur(id_composant_ordinateur)
);

CREATE TABLE ordinateur_client(
   id_ordinateur INTEGER,
   id_client VARCHAR(50) ,
   PRIMARY KEY(id_ordinateur, id_client),
   FOREIGN KEY(id_ordinateur) REFERENCES ordinateur(id_ordinateur),
   FOREIGN KEY(id_client) REFERENCES client(id_client)
);

CREATE TABLE composant_recommande(
   id_composant_recommande SERIAL,
   annee INTEGER NOT NULL CHECK(annee >= 1980 ),
   description TEXT,
   date_recommandation DATE DEFAULT NOW(),
   id_composant INTEGER NOT NULL,
   UNIQUE(date_recommandation, id_composant),
   PRIMARY KEY(id_composant_recommande),
   FOREIGN KEY(id_mois) REFERENCES mois(id_mois),
   FOREIGN KEY(id_composant) REFERENCES composant(id_composant)
);