-- Données pour la table technicien
INSERT INTO technicien (nom_technicien) VALUES
('Jean Dupont'),
('Marie Curie'),
('Albert Einstein'),
('Ada Lovelace');

-- Données pour la table type_ordinateur
INSERT INTO type_ordinateur (nom_type_ordinateur) VALUES
('Ordinateur Portable'),
('Ordinateur de Bureau'),
('Serveur'),
('Ordinateur Tout-en-Un');

-- Données pour la table ordinateur
INSERT INTO ordinateur (nom_ordinateur, description, id_type_ordinateur) VALUES
('HP Pavilion 15', 'Ordinateur portable léger et performant', 1),
('Dell OptiPlex 5090', 'Ordinateur de bureau fiable', 2),
('HPE ProLiant DL360', 'Serveur rack haute performance', 3),
('iMac 24"', 'Ordinateur tout-en-un avec écran Retina', 4);

-- Données pour la table type_composant
INSERT INTO type_composant (nom_type_composant) VALUES
('Processeur'),
('Mémoire RAM'),
('Disque Dur'),
('Ecran'),
('Carte Graphique');

-- Données pour la table composant
INSERT INTO composant (nom_composant, capacite, prix_unitaire, id_type_composant) VALUES
('Intel Core i7', 3.6, 300.00, 1),
('Kingston DDR4 16GB', 16.0, 80.00, 2),
('Samsung SSD 1TB', 1024.0, 150.00, 3),
('ASUS ROG STRIX B550', 0, 200.00, 4),
('NVIDIA GeForce RTX 3060', 6.0, 400.00, 5);

-- Données pour la table reparation
INSERT INTO reparation (date_reparation, id_ordinateur) VALUES
('2025-01-01', 1),
('2025-01-02', 2)
;

-- Données pour la table composant_ordinateur
INSERT INTO composant_ordinateur (id_composant, id_ordinateur) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 2);

-- Données pour la table type_reparation
INSERT INTO type_reparation (nom_type_reparation) VALUES
('Remplacement'),
('Réparation'),
('Nettoyage'),
('Mise à jour');

-- Données pour la table composant_reparation
INSERT INTO composant_reparation (id_reparation, id_technicien, id_type_reparation, id_composant_ordinateur) VALUES
(1, 1, 1, 1),
(1, 2, 2, 2),
(1, 3, 3, 3),
(2, 4, 4, 4),
(2, 4, 4, 5);

