create table Themes ( 
	idTheme integer primary key,
	libelleTheme varchar(50) not null
	);

INSERT INTO Themes (idTheme,libelleTheme) VALUES (1,'SQL');
INSERT INTO Themes (idTheme,libelleTheme) VALUES (2,'Big Data');
INSERT INTO Themes (idTheme,libelleTheme) VALUES (3,'AI');
INSERT INTO Themes (idTheme,libelleTheme) VALUES (4,'Machine learning');
INSERT INTO Themes (idTheme,libelleTheme) VALUES (5,'hacking');


create table Prestataires (
	idPres integer primary key,
	libellePres varchar(50) not null
	);

INSERT INTO Themes (idPres,libellePres) VALUES (1,'');
INSERT INTO Themes (idPres,libellePres) VALUES (2,'');
INSERT INTO Themes (idPres,libellePres) VALUES (3,'');
INSERT INTO Themes (idPres,libellePres) VALUES (4,'');
INSERT INTO Themes (idPres,libellePres) VALUES (5,'');
INSERT INTO Themes (idPres,libellePres) VALUES (6,'');


create table Seminaires (
	idSemi integer primary key,
	libelleSemi varchar(50) not null,
	dureeSemi integer(1) not null,
	etatSemi varchar(5) not null,
	prixPers float(5) not null,
	dateSemi date not null,
	nbPers integer not null check(nbPers > 0),
	idTheme integer references Themes(idTheme),
	idPres integer references Prestataires(idPres),
	idSalle integer references Salles(idSalle),
	idRepa integer references Repas(idRepa),
	);

INSERT INTO Seminaires (idSemi,libelleSemi,dureeSmi,etatSemi,prixPers,idTheme,idPres,idSalle,idRepa) VALUES (1,'',,);



create table Personnes (
	idPers integer primary key,
	nomPers varchar(50),
	prenomPers varchar(50),
	typePers varchar(20),
	);

INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (1,'','','CON');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (2,'','','CON');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (3,'','','CON');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (4,'','','ACT');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (5'','','ACT');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (6,'','','ACT');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (7,'','','EMP');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (8,'','','EMP');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (9,'','','EMP');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (10,'','','EMP');
INSERT INTO Personnes (idPers,nomPers,prenomPers,typePers) VALUES (11,'','','EMP');



create table Activites (
	idAct integer primary key,
	libelleAct varchar(50) not null
	);

INSERT INTO Activites (idAct,libelleAct) VALUES (1,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (2,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (3,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (4,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (5,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (6,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (7,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (8,'');
INSERT INTO Activites (idAct,libelleAct) VALUES (9,'');



create table Salles (
	idSalle integer primary key,
	libelleSalle varchar(50),
	nbPlace integer not null
	);

INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (1,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (2,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (3,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (4,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (5,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (6,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (7,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (8,'',);
INSERT INTO Salles (idSalle,libelleSalle,nbPlace) VALUES (9,'',);

create table Pauses (
	idPause integer primary key,
	libellePause varchar(50)
	);


INSERT INTO Pauses (idPause,libellePause) VALUES (1,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (2,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (3,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (4,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (5,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (6,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (7,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (8,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (9,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (10,'');
INSERT INTO Pauses (idPause,libellePause) VALUES (11,'');

create table Repas (
	idRepa integer primary key,
	libelleRepa varchar(50)
	);

INSERT INTO Repas (idRepa,libelleRepa) VALUES (1,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (2,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (3,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (4,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (5,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (6,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (7,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (8,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (9,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (10,'');
INSERT INTO Repas (idRepa,libelleRepa) VALUES (11,'');

create table PrixSalles (
	idPres integer,
	idSalle integer,
	prix float(5),
	primary key (idPres,idSalle),
	foreign key (idPres) references Prestataires(idPers),
	foreign key (idSalle) references Salles(idSalle)
);

INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);
INSERT INTO PrixS (idPres,idSalle,prix) VALUES (1,1,50);



create table PrixPauses (
	idPres integer,
	idPause integer,
	prix float(5),
	primary key (idPres,idPause),
	foreign key idPres references Prestataires(idPers),
	foreign key idPause references Pauses(idPause)
);

INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);
INSERT INTO PrixP (idPres,idPause,prix) VALUES (1,1,50);


create table PrixRepas (
	idPres integer,
	idRepa integer,
	prix float(5),
	primary key (idPres,idRepa),
	foreign key idPres references Prestataires(idPers),
	foreign key idRepa references Salles(idRepa)
);

INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);
INSERT INTO PrixR (idPres,idRepa,prix) VALUES (1,1,50);


create table Participe (
	idPers integer,
	idSemi integer,
	primary key (idPers,idSemi),
	foreign key idPers references Personnes(idPers),
	foreign key idSemi references Seminaires(idSemi)
);

INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);
INSERT INTO Participe (idPers,idSemi) VALUES (1,1);

create table ListeAtt (
	idPers integer,
	isSemi integer, 
	nbFile integer,
	primary key (idPers,idSemi),
	foreign key idPers references Personnes(idPers),
	foreign key idSemi references Seminaires(idSemi)
);

INSERT INTO ListeAtt (idPers,idSemi,nbFile) VALUES (1,1,);
INSERT INTO ListeAtt (idPers,idSemi,nbFile) VALUES (1,1,);
INSERT INTO ListeAtt (idPers,idSemi,nbFile) VALUES (1,1,);
INSERT INTO ListeAtt (idPers,idSemi,nbFile) VALUES (1,1,);
INSERT INTO ListeAtt (idPers,idSemi,nbFile) VALUES (1,1,);

create table Organise (
	idPers integer,
	ididSemi integer,
	primary key (idPers,idSemi),
	foreign key idPers references Personnes(idPers),
	foreign key idSemi references Seminaires(idSemi)
);

INSERT INTO Organise (idPers,idSemi) VALUES (1,1);
INSERT INTO Organise (idPers,idSemi) VALUES (1,1);
INSERT INTO Organise (idPers,idSemi) VALUES (1,1);
INSERT INTO Organise (idPers,idSemi) VALUES (1,1);


create table FaitUneConf (
	idPers integer,
	idSemi integer,
	prix float(5) not null,
	support varchar(100),
	titre varchar(50),
	primary key (idPers,idSemi),
	foreign key idPers references Personnes(idPers),
	foreign key idSemi references Seminaires(idSemi)
);

INSERT INTO FaitUneConf (idPers,idSemi,prix,support,titre) VALUES (1,1,10,'','');
INSERT INTO FaitUneConf (idPers,idSemi,prix,support,titre) VALUES (1,1,10,'','');
INSERT INTO FaitUneConf (idPers,idSemi,prix,support,titre) VALUES (1,1,10,'','');
INSERT INTO FaitUneConf (idPers,idSemi,prix,support,titre) VALUES (1,1,10,'','');
INSERT INTO FaitUneConf (idPers,idSemi,prix,support,titre) VALUES (1,1,10,'','');


create table Prevue (
	idAct integer,
	idSemi integer,
	 primary key (idAct,idSemi),
	 foreign key idAct references Activite(idAct),
	 foreign key idSemi references Seminaires(idSemi)
);

INSERT INTO Prevue (idAct,idSemi) VALUES (1,1);
INSERT INTO Prevue (idAct,idSemi) VALUES (1,1);
INSERT INTO Prevue (idAct,idSemi) VALUES (1,1);
INSERT INTO Prevue (idAct,idSemi) VALUES (1,1);
INSERT INTO Prevue (idAct,idSemi) VALUES (1,1);

