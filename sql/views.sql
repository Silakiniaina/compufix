CREATE
OR REPLACE VIEW v_etat_stock_approvisionnement AS
SELECT
    c.id_composant,
    c.nom_composant,
    COALESCE(
        SUM(
            CASE
                WHEN m.est_entree THEN m.quantite_composant
                ELSE 0
            END
        ),
        0
    ) AS total,
    COALESCE(
        SUM(
            CASE
                WHEN NOT m.est_entree THEN m.quantite_composant
                ELSE 0
            END
        ),
        0
    ) AS utilise,
    COALESCE(
        SUM(
            CASE
                WHEN m.est_entree THEN m.quantite_composant
                ELSE 0
            END
        ),
        0
    ) - COALESCE(
        SUM(
            CASE
                WHEN NOT m.est_entree THEN m.quantite_composant
                ELSE 0
            END
        ),
        0
    ) AS restant,
    CASE
        WHEN COALESCE(
            SUM(
                CASE
                    WHEN m.est_entree THEN m.quantite_composant
                    ELSE 0
                END
            ),
            0
        ) - COALESCE(
            SUM(
                CASE
                    WHEN NOT m.est_entree THEN m.quantite_composant
                    ELSE 0
                END
            ),
            0
        ) < 10 THEN TRUE -- Seuil fixé à 50
        ELSE FALSE
    END AS besoin_approvisionnement
FROM
    composant c
    LEFT JOIN mouvement_stock m ON c.id_composant = m.id_composant
GROUP BY
    c.id_composant,
    c.nom_composant;

-- statistique 
CREATE
OR REPLACE VIEW v_statistique_mouvement AS
WITH
    entrees AS (
        SELECT
            id_composant,
            SUM(quantite_composant) AS total_entrees,
            COUNT(*) AS nombre_entrees
        FROM
            mouvement_stock
        WHERE
            est_entree = true
        GROUP BY
            id_composant
    ),
    sorties AS (
        SELECT
            id_composant,
            SUM(quantite_composant) AS total_sorties,
            COUNT(*) AS nombre_sorties
        FROM
            mouvement_stock
        WHERE
            est_entree = false
        GROUP BY
            id_composant
    )
SELECT
    c.id_composant,
    c.nom_composant,
    COALESCE(e.nombre_entrees, 0) AS nombre_entrees,
    COALESCE(e.total_entrees, 0) AS total_entrees,
    COALESCE(s.nombre_sorties, 0) AS nombre_sorties,
    COALESCE(s.total_sorties, 0) AS total_sorties
FROM
    composant c
    LEFT JOIN entrees e ON c.id_composant = e.id_composant
    LEFT JOIN sorties s ON c.id_composant = s.id_composant
ORDER BY
    total_sorties DESC;

CREATE
OR REPLACE VIEW v_statistique_moyenne_sortie AS
WITH
    sorties_filtrees AS (
        SELECT
            id_composant,
            SUM(quantite_composant) AS total_sorties_filtrees,
            COUNT(*) AS nombre_sorties_filtrees
        FROM
            mouvement_stock
        WHERE
            est_entree = false
        GROUP BY
            id_composant
    ),
    sorties_actuelles AS (
        SELECT
            id_composant,
            SUM(quantite_composant) AS total_sorties_actuelles
        FROM
            mouvement_stock
        WHERE
            est_entree = false
        GROUP BY
            id_composant
    )
SELECT
    c.id_composant,
    c.nom_composant,
    COALESCE(sa.total_sorties_actuelles, 0) AS sortie_actuelle,
    CASE
        WHEN sf.nombre_sorties_filtrees > 0 THEN ROUND(
            COALESCE(sf.total_sorties_filtrees, 0) / sf.nombre_sorties_filtrees::NUMERIC,
            2
        )
        ELSE 0
    END AS moyenne_sorties
FROM
    composant c
    LEFT JOIN sorties_filtrees sf ON c.id_composant = sf.id_composant
    LEFT JOIN sorties_actuelles sa ON c.id_composant = sa.id_composant;

CREATE
OR REPLACE VIEW v_composant_ordinateur AS
SELECT
    co.*,
    c.id_type_composant
FROM
    composant_ordinateur co
    JOIN composant c ON co.id_composant = c.id_composant;

CREATE
OR REPLACE VIEW v_ordinateur AS
SELECT
    o.*,
    SUM(c.prix_unitaire) AS prix
FROM
    ordinateur o
    JOIN composant_ordinateur co ON o.id_ordinateur = co.id_ordinateur
    JOIN composant c ON co.id_composant = c.id_composant
GROUP BY
    o.id_ordinateur;

-- view pour la liste de reparation par  type composant
CREATE OR REPLACE VIEW v_reparation AS 
SELECT
    r.id_reparation,
    r.date_reparation,
    tc.id_type_composant,
    c.id_composant,
    co.id_ordinateur,
    CASE 
        WHEN r.id_reparation IN (SELECT id_reparation FROM retour_reparation) THEN true
        ELSE false
    END AS est_retourne
FROM
    reparation r
JOIN composant_reparation cr ON r.id_reparation = cr.id_reparation
JOIN composant_ordinateur co ON cr.id_composant_ordinateur = co.id_composant_ordinateur
JOIN composant c ON co.id_composant = c.id_composant
JOIN type_composant tc ON c.id_type_composant = tc.id_type_composant;


-- liste retour
CREATE OR REPLACE VIEW v_retour_reparation AS
SELECT 
    rr.id_retour_reparation,
    rr.date_retour,
    rr.id_reparation,
    rr.prix_total,
    o.id_ordinateur,
    o.id_client,
    ARRAY_AGG(DISTINCT o.id_type_ordinateur) as types_ordinateur,
    ARRAY_AGG(DISTINCT cr.id_type_reparation) as types_reparation,
    ARRAY_AGG(DISTINCT c.id_type_composant) as types_composant
FROM retour_reparation rr
JOIN reparation r ON r.id_reparation = rr.id_reparation
JOIN ordinateur o ON o.id_ordinateur = r.id_ordinateur
JOIN composant_reparation cr ON cr.id_reparation = r.id_reparation
JOIN composant_ordinateur co ON co.id_composant_ordinateur = cr.id_composant_ordinateur
JOIN composant c ON c.id_composant = co.id_composant
GROUP BY 
    rr.id_retour_reparation,
    rr.date_retour,
    rr.id_reparation,
    rr.prix_total,
    o.id_ordinateur,
    o.id_client;

-- view commission technicien : 
CREATE OR REPLACE VIEW v_commission_technicien_composant AS
SELECT 
    cr.id_reparation,
    cr.id_technicien,
    cr.id_type_reparation,
    cr.id_composant_ordinateur,
    cr.prix,
    (cr.prix * 0.05) AS commission_technicien 
FROM 
    composant_reparation cr;

CREATE OR REPLACE VIEW v_commission_technicien_composant AS
SELECT 
    cr.id_reparation,
    cr.id_technicien,
    cr.id_type_reparation,
    cr.id_composant_ordinateur,
    cr.prix,
    CASE 
        WHEN cr.prix >= 200000 THEN (cr.prix * 0.05)
        ELSE 0
    END AS commission_technicien 
FROM 
    composant_reparation cr;
    

CREATE OR REPLACE VIEW v_commission_technicien_complet AS
SELECT 
    r.id_reparation,
    r.date_reparation,
    vctc.id_technicien,
    vctc.prix,
    vctc.commission_technicien
FROM 
    reparation r
JOIN 
    v_commission_technicien_composant vctc 
ON r.id_reparation = vctc.id_reparation
;

CREATE OR REPLACE VIEW v_commission_technicien_genre AS 
  select 
    g.id_genre,
    count(id_reparation) as nombre_reparation,
    sum(prix) as total_reparation,
    sum(commission_technicien) as total_commission
  from 
    v_commission_technicien_complet v
  JOIN technicien t 
  ON t.id_technicien = v.id_technicien 
  JOIN genre g 
  ON g.id_genre = t.id_genre
  GROUP BY g.id_genre
 ;

 CREATE OR REPLACE VIEW v_composant_complet AS 
	SELECT 
    c.*,
    COALESCE(
        (SELECT hpc.nouveau_prix
         FROM historique_prix_composant hpc
         WHERE hpc.id_composant = c.id_composant
           AND hpc.date_modification <= CURRENT_DATE
         ORDER BY hpc.date_modification DESC
         LIMIT 1),
        (SELECT hpc.nouveau_prix
         FROM historique_prix_composant hpc
         WHERE hpc.id_composant = c.id_composant
         ORDER BY hpc.date_modification DESC
         LIMIT 1)
    ,c.prix_unitaire) AS prix_actuel
FROM 
    composant c;