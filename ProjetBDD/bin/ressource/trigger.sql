
-- ----------------------------------------------------------------------
-- TRIGGER INSERT verif_organise_animateur
--

-- Permet de verifier si la personne qui organise une activite est bien un animateur 

CREATE OR REPLACE TRIGGER verif_organise_animateur BEFORE INSERT
ON  Organise
DECLARE
	type varchar(3);
BEGIN
	select typePers into type
	from Personnes
	where idPers = :New.idPers;
	IF(type <> 'ANI')THEN
		RAISE_APPLICATION_ERROR (-20010 , 'ERREUR : Seulement les animateurs peuvent organiser un Seminaire');
	END IF;
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT verif_conferencier_faitUneConf
--

--Permet de verifier que les personnes intervenant dans une conference sont bien des conferenciers

CREATE OR REPLACE TRIGGER verif_conferencier_faitUneConf BEFORE INSERT
ON FaitUneConf
DECLARE
	type varchar(3);
BEGIN
	select typePers into type
	from Personnes
	where idPers = :New.idPers;
	IF(type <> 'CON')THEN
		RAISE_APPLICATION_ERROR (-20020 , 'ERREUR : Seulement les conferencier peuvent intervenir dans un seminaire');
	END IF;
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT verif_employe_participe
--
CREATE OR REPLACE TRIGGER verif_employe_participe BEFORE INSERT
ON Participe
DECLARE
	type varchar(3);
BEGIN
	IF(type <> 'EMP')THEN
		RAISE_APPLICATION_ERROR (-20030 , 'ERREUR : Seulement les emplye peuvent participer au seminaire');
	END IF;
EXCEPTION
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT verif_duree_dejeuner
--

-- Permet de verifier si la conference est sur un jour complet alors il faut absolument un dejeuner

CREATE OR REPLACE TRIGGER verif_duree_dejeuner BEFORE INSERT
ON Seminaires
BEGIN
	IF(:New.dureeSemi = 'JOU' AND :New.dejeuner = 0)THEN
		RAISE_APPLICATION_ERROR (-20100 , 'ERREUR : Lors d''un seminaire d''une journee il faut avoir un dejeuner');
	END IF;
END;
/




-- ----------------------------------------------------------------------
-- TRIGGER INSERT insert_activite_demi_jour
--

-- Regarde si il y a pas plus de 3 activitées 
-- par séminaire pour une demi journée

CREATE OR REPLACE TRIGGER insert_activite_demi_jour BEFORE INSERT
ON Prevu
DECLARE
	counter INTEGER;
	counterSupp3Excep EXCEPTION;
BEGIN
	SELECT COUNT(*) INTO counter
	FROM Prevu natural join Seminaire
	WHERE New.idSemi = idSemi
		AND typeJour = 1 OR typeJour = 2;
	IF(counter > 3)THEN
		RAISE counterSupp3Excep;
	END IF;
EXCEPTION
	WHEN counterSupp3Excep THEN DBMS_OUTPUT.PUT_LINE('Nombre dactivite maximum atteint');
	WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('insertion correct');
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT insert_activite_journee
--

-- Regarde si il y a pas plus de 6 activitées 
-- par séminaire pour une journée

CREATE OR REPLACE TRIGGER insert_activite_journee BEFORE INSERT
ON Prevu
DECLARE
	counter INTEGER;
	counterSupp6Excep EXCEPTION;
BEGIN
	SELECT COUNT(*) INTO counter
	FROM Prevu natural join Seminaire
	WHERE New.idSemi = idSemi
		AND typeJour = 3;
	IF(counter > 6)THEN
		RAISE counterSupp6Excep;
	END IF;
EXCEPTION
	WHEN counterSupp6Excep THEN DBMS_OUTPUT.PUT_LINE('Nombre d activite maximum atteint');
	WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('insertion correct');
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT insert_seminaire_max
--

--

CREATE OR REPLACE TRIGGER insert_seminaire_max BEFORE INSERT
ON seminaire
DECLARE
	counter INTEGER;
	counterSupp3Excep EXCEPTION;
BEGIN
	SELECT COUNT(*) INTO counter
	FROM seminaire
	WHERE New.dateSemi = dateSemi;
	IF(counter > 3)THEN
		RAISE counterSupp3Excep;
	END IF;
EXCEPTION
	WHEN counterSupp3Excep THEN DBMS_OUTPUT.PUT_LINE('Nombre de seminaire maximum atteint');
	WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('insertion correct');
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT insert_personne_max_participe
--

-- 

CREATE OR REPLACE TRIGGER insert_personne_max_participe BEFORE INSERT
ON Participe
DECLARE
	nombreDePersonneMax INTEGER;
	counter INTEGER;
BEGIN
	SELECT COUNT(*) INTO counter, nbPers INTO nombreDePersonneMax
	FROM Participe natural join Seminaire
	WHERE New.idSemi = idSemi
	GROUP BY idSemi, nbPers;
	IF(counter > nombreDePersonneMax)THEN
		RAISE_APPLICATION_ERROR (-20002, 'Imposible');
	END IF;
EXCEPTION
	WHEN OTHERS THEN DBMS_OUTPUT.PUT_LINE('insertion correct');
END;
/

-- ----------------------------------------------------------------------
-- TRIGGER INSERT 
--


/*
 * CREATE OR REPLACE TRIGGER nom_tigger BEFORE INSERT
ON _table FOR EACH ROW
DECLARE
	
BEGIN
	IF()THEN
		
	END IF;
EXCEPTION
	
END;
/
 * 
 */*/



