CREATE TABLE classes(
	class_id serial PRIMARY KEY,
	name TEXT,
	casting bool,
	priority1 TEXT,
	priority2 TEXT,
	save1 TEXT,
	save2 TEXT
);


INSERT INTO classes(name, casting, priority1, priority2, save1, save2)
VALUES ('Barbarian', FALSE, 'str','con','str', 'con'),
	   ('Bard', TRUE, 'cha', 'inte', 'dex', 'cha'),
	   ('Cleric', TRUE, 'wis', 'cha','wis', 'cha'),
	   ('Druid', TRUE, 'wis', 'dex', 'inte', 'wis'),
	   ('Fighter', FALSE, 'str', 'con','str', 'con'),
	   ('Monk', FALSE, 'dex', 'wis', 'str', 'dex'),
	   ('Paladin', TRUE, 'cha', 'str', 'wis', 'cha'),
	   ('Ranger', TRUE, 'wis', 'dex', 'str', 'dex'),
	   ('Rogue', FALSE, 'dex', 'cha', 'dex', 'inte'),
	   ('Sorcerer', TRUE, 'cha', 'inte', 'con', 'cha'),
	   ('Warlock', TRUE, 'cha', 'inte', 'wis', 'cha'),
	   ('Wizard', TRUE, 'inte', 'wis', 'inte', 'wis');

SELECT * FROM classes;

CREATE TABLE abilities(
	ability_id serial PRIMARY KEY,
	ability TEXT
);

INSERT INTO abilities(ability)
VALUES ('2 Rage with +2 to damage'), ('Unarmored Defense'),
	   ('2 cantrips'), ('2 1st level spells'), ('Bardic Inspiration'),
	   ('3 cantrips'), ('Divine Domain'),
	   ('Druidic'),
	   ('Great Weapon Fighter'), ('Second Wind'),
	   ('Unarmored Defense'), ('Martial Arts'),
	   ('Divine Sense'), ('Lay on Hands'),
	   ('Favored Enemy'), ('Natural Explorer'),
	   ('Expertise'), ('Sneak Attack'), ('Thieve''s Cant'),
	   ('4 cantrips'), ('+1 hp'),
	   ('Otherworldy Patron'),
	   ('Arcane Recovery');

CREATE TABLE class_abilities(
	classability_id serial PRIMARY KEY,
	class_id_fk int REFERENCES classes(class_id),
	ability_id_fk int REFERENCES abilities(ability_id)
);

SELECT name, ability FROM classes JOIN class_abilities ON class_id_fk = class_id JOIN abilities ON ability_id = ability_id_fk;

-- 2 cantrips 3, 2 1st level spells 4
INSERT INTO class_abilities(class_id_fk, ability_id_fk)
VALUES (1, 1), (1, 2),
	   (2, 3), (2, 4), (2,5),
	   (3, 6), (3, 4), (3,7),
	   (4, 3), (4, 4), (4,8),
	   (5, 9), (5, 10),
	   (6, 11), (6, 12),
	   (7, 13), (7, 14),
	   (8, 15), (8, 16),
	   (9, 17), (9, 18), (9,19),
	   (10, 20), (10, 4), (10, 21),
	   (11, 3), (11, 4), (11, 22),
	   (12, 6), (12, 4), (12, 23);


SELECT * FROM class_abilities;

------------------------
SELECT classes.name, abilities.ability, classes.priority1, classes.priority2, classes.save1, classes.save2 
FROM classes 
LEFT OUTER JOIN class_abilities 
ON classes.class_id = class_abilities.class_id_fk
JOIN abilities
ON abilities.ability_id = class_abilities.ability_id_fk;
--WHERE classes.name = 'Barbarian';
-----------------------


-------------------------------------------------------------------------

CREATE TABLE races(
	race_id serial PRIMARY KEY,
	name TEXT
);

--DROP TABLE races CASCADE;

INSERT INTO races(name)
VALUES ('Dragonborn'),
	   ('Dwarf'),
	   ('Elf'),
	   ('Gnome'),
	   ('Half-Elf'),
	   ('Halfling'),
	   ('Half-Orc'),
	   ('Human'),
	   ('Tiefling');

CREATE TABLE traits(
	trait_id serial PRIMARY KEY,
	trait TEXT
);

INSERT INTO traits(trait)
VALUES ('Breath Weapon'), ('Fire Resistance'),
	   ('Darkvision'), ('Dwarven Resilience'),
	   ('Trance'),
	   ('Gnome Cunning'),
	   ('Skill Versatility'),
	   ('Lucky'), ('Brave'), ('Halfling Nimbleness'),
	   ('Menacing'), ('Relentless Endurance'),
	   ('Extra Language'),
	   ('Hellish Resistance'), ('Infernal Legacy');
	  
CREATE TABLE race_trait(
	traitxrace_id serial PRIMARY KEY,
	trait_id_fk int REFERENCES traits(trait_id),
	race_id_fk int REFERENCES races(race_id)
);

SELECT * FROM adds;
SELECT races.name, traits.trait FROM races 
LEFT OUTER JOIN race_trait 
ON races.race_id = race_trait.race_id_fk
JOIN traits
ON traits.trait_id = race_trait.trait_id_fk;


INSERT INTO race_trait(race_id_fk, trait_id_fk)
VALUES (1, 1), (1, 2), 
       (2, 3), (2, 4), 
       (3, 3), (3, 5), 
       (4, 3), (4, 6),
       (5, 3), (5, 7),
       (6, 8), (6, 9), (6, 10),
       (7, 3), (7, 11), (7, 12),
       (8, 13),
       (9, 3), (9, 14), (9, 15);
      
CREATE TABLE adds(
	add_id serial PRIMARY KEY,
	race_id_fk int REFERENCES races(race_id),
	str int,
	dex int,
	con int,
	inte int,
	wis int,
	cha int
);

INSERT INTO adds (race_id_fk, str, dex, con, inte, wis, cha)
     -- r  s  d  co i  w  ch
VALUES (1, 2, 0, 0, 0, 0, 1),
	   (2, 0, 2, 0, 0, 0, 0),
	   (3, 0, 2, 0, 0, 0, 0),
	   (4, 0, 0, 0, 2, 0, 0),
	   (5, 0, 1, 0, 1, 0, 2),
	   (6, 0, 2, 0, 0, 0, 0),
	   (7, 2, 0, 1, 0, 0, 0),
	   (8, 1, 1, 1, 1, 1, 1),
	   (9, 0, 0, 0, 1, 0, 2);

SELECT * FROM adds;

---------------------
SELECT races.race_id, races.name, traits.trait, adds.str, adds.dex, adds.con, adds.inte, adds.wis, adds.cha FROM races 
LEFT OUTER JOIN race_trait 
ON races.race_id = race_trait.race_id_fk
JOIN traits
ON traits.trait_id = race_trait.trait_id_fk
JOIN adds
ON adds.race_id_fk = races.race_id;
---------------------

SELECT races.name, adds.str, adds.dex, adds.con, adds.inte, adds.wis, adds.cha 
FROM adds JOIN races ON race_id = adds.race_id_fk;-- JOIN adds ON adds.race_id_fk=race_id;



CREATE TABLE characters(
	character_id serial PRIMARY KEY,
	class_id_fk int REFERENCES classes(class_id),
	race_id_fk int REFERENCES races(race_id),
	f_name TEXT NOT NULL,
	l_name TEXT NOT NULL,
	alignment TEXT
);

CREATE TABLE abilityscores(
	abilityscore_id serial PRIMARY KEY,
	character_id_fk int REFERENCES characters(character_id),
	str int,
	dex int,
	con int,
	inte int,
	wis int,
	cha int
);

SELECT * FROM CHARACTERS;
SELECT character_id, f_name, l_name, classes.name, races.name,
abilityscores.str, abilityscores.dex, abilityscores.con,
abilityscores.inte, abilityscores.wis, abilityscores.cha
FROM CHARACTERS JOIN classes ON class_id = class_id_fk
JOIN races ON race_id = race_id_fk JOIN abilityscores
ON character_id_fk = character_id;


SELECT * FROM races JOIN adds ON race_id_fk = race_id WHERE race_id=1;

DELETE FROM abilityscores WHERE character_id_fk = 2;
DELETE FROM CHARACTERS WHERE character_id = 2;