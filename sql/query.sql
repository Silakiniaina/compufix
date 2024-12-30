-- etat stock 
SELECT 
	*
FROM 
    v_etat_stock
WHERE 
    id_composant IN (
        SELECT DISTINCT id_composant
        FROM mouvement_stock
        WHERE date_mouvement <= '2026-12-30'
    );

    