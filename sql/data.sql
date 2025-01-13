INSERT INTO type_composant(nom_type_composant) VALUES
    ('Disque'),
    ('CPU'),
    ('RAM'),
    ('Ecran')
;

INSERT INTO reparation(id_ordinateur) VALUES 
    (4),
    (4),
    (8),
    (8),
    (8)
;

INSERT INTO type_composant_reparation(id_type_composant, id_reparation) VALUES 
    (6,1),
    (5,2),
    (4,3),
    (6,4),
    (2,5)
;