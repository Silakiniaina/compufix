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