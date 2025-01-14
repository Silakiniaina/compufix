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

-- CREATE
-- OR REPLACE VIEW v_installation_ram AS
-- SELECT
--     c.*,
--     r.id_ram,
--     r.est_portable,
--     r.id_type_ram,
--     cmu.id_carte_mere,
--     cmu.type_slot,
--     cmu.date_installation,
--     co.id_ordinateur
-- FROM
--     ram r
--     JOIN composant c ON r.id_composant = c.id_composant
--     JOIN carte_mere_utilisation cmu ON r.id_composant = cmu.id_composant
--     JOIN composant_ordinateur co ON r.id_composant = co.id_composant;

-- CREATE
-- OR REPLACE VIEW v_installation_processeur AS
-- SELECT
--     c.*,
--     p.id_processeur,
--     p.id_type_processeur,
--     p.nombre_coeur,
--     p.generation,
--     cmu.id_carte_mere,
--     cmu.type_slot,
--     cmu.date_installation,
--     co.id_ordinateur
-- FROM
--     processeur p
--     JOIN composant c ON p.id_composant = c.id_composant
--     JOIN carte_mere_utilisation cmu ON p.id_composant = cmu.id_composant
--     JOIN composant_ordinateur co ON c.id_composant = co.id_composant;

-- CREATE
-- OR REPLACE VIEW v_installation_disque AS
-- SELECT
--     c.*,
--     r.id_disque_dur,
--     r.est_portable,
--     r.id_type_disque,
--     cmu.id_carte_mere,
--     cmu.type_slot,
--     cmu.date_installation,
--     co.id_ordinateur
-- FROM
--     disque_dur r
--     JOIN composant c ON r.id_composant = c.id_composant
--     JOIN carte_mere_utilisation cmu ON r.id_composant = cmu.id_composant
--     JOIN composant_ordinateur co ON r.id_composant = co.id_composant;

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
        WHEN r.id_reparation IN (SELECT id_reparation FROM retour) THEN true
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
SELECT DISTINCT
    rr.id_retour_reparation,
    rr.date_retour,
    rr.id_reparation,
    rr.prix_total,
    o.id_ordinateur,
    o.id_type_ordinateur,
    cr.id_type_reparation,
    c.id_type_composant
FROM retour_reparation rr
JOIN reparation r ON r.id_reparation = rr.id_reparation
JOIN ordinateur o ON o.id_ordinateur = r.id_ordinateur
JOIN composant_reparation cr ON cr.id_reparation = r.id_reparation
JOIN composant_ordinateur co ON co.id_composant_ordinateur = cr.id_composant_ordinateur
JOIN composant c ON c.id_composant = co.id_composant;