CREATE
OR REPLACE VIEW v_etat_stock AS
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
    ) AS restant
FROM
    composant c
    LEFT JOIN mouvement_stock m ON c.id_composant = m.id_composant
GROUP BY
    c.id_composant,
    c.nom_composant;