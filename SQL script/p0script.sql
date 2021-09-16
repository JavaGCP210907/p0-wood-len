
-- 0NF
CREATE TABLE avengers(
	hero_name TEXT,
	hero_power TEXT,
	real_name TEXT,
	home_base TEXT,
	home_address TEXT
);


INSERT INTO avengers(hero_name, hero_power, real_name, home_base, home_address)
VALUES ('Spider-man', 'Webby Boi', 'Peter Parker', 'Aunt May''s house', 'New York'),
	   ('Hawkeye', 'bow and arrow',  'Clint Barton', 'A Farm', 'Upstate New York');

SELECT * FROM avengers;	  

DROP TABLE IF EXISTS avengers;

-- 1NF
-- must have a primary key, can be a composite key, columns must be atomic
CREATE TABLE avengers(
	hero_name TEXT,
	hero_power TEXT,
	f_name TEXT,
	l_name TEXT,
	home_base TEXT,
	street_number TEXT,
	city TEXT,
	state char(2),
	PRIMARY KEY(f_name, l_name)
);

INSERT INTO avengers(hero_name, hero_power, f_name, l_name, home_base, street_number, city, state)
VALUES ('Spider-man', 'Webby Boi', 'Peter', 'Parker', 'Aunt May''s house', '225', 'Queens', 'NY'),
	   ('Spider-man', 'Webby Shocky Boi', 'Miles', 'Morales', 'Aunt May''s house', '225', 'Queens', 'NY'),
	   ('Thor', 'Hammer Boi',  'Thor', 'Odinson', 'Asgard', '5678', 'New Asgard', 'NO');
	   

-- 2NF
-- Remove partial dependencies
-- You can eliminate partial dependencies by having a single column primary KEY, no composite key
CREATE TABLE avengers(
	avenger_id serial PRIMARY KEY,
	hero_name TEXT,
	hero_power TEXT,
	f_name TEXT,
	l_name TEXT,
	home_base TEXT,
	street_number TEXT,
	city TEXT,
	state char(2)
);

INSERT INTO avengers(hero_name, hero_power, f_name, l_name, home_base, street_number, city, state)
VALUES ('Spider-man', 'Webby Boi', 'Peter', 'Parker', 'Aunt May''s house', '225', 'Queens', 'NY'),
	   ('Spider-man', 'Webby Shocky Boi', 'Miles', 'Morales', 'Aunt May''s house', '225', 'Queens', 'NY'),
	   ('Thor', 'Hammer Boi',  'Thor', 'Odinson', 'Asgard', '5678', 'New Asgard', 'NO');
	   
SELECT * FROM avengers;

-- 3NF
-- Remove Transitive Dependencies (by separating them into new tables)
-- the only columns depended on should be primary keys

DROP TABLE IF EXISTS avengers;

CREATE TABLE homes(
	home_base TEXT PRIMARY KEY,
	street_number TEXT,
	city TEXT,
	state char(2)
);

INSERT INTO homes (home_base, street_number, city, state)
VALUES ('Stark Tower', '455', 'Manhattan', 'NY'),
	   ('Sanctum Sanctorum', '860', 'Greenwich', 'CT'),
	   ('Avengers Tower', '763', 'New York', 'NY');
	   
CREATE TABLE avengers(
	hero_id serial PRIMARY KEY,
	hero_name TEXT,
	hero_power TEXT,
	f_name TEXT,
	l_name TEXT,
	home_base_fk TEXT REFERENCES homes(home_base)
);

INSERT INTO avengers(hero_name, hero_power, f_name, l_name, home_base_fk)
VALUES ('Iron Man', 'Money', 'Tony', 'Stark', 'Stark Tower'),
	   ('Doctor Strange', 'Time Wizard', 'Stephen', 'Strange', 'Sanctum Sanctorum');


SELECT * FROM avengers;

-- joins

-- inner join
SELECT * FROM avengers JOIN homes ON home_base = home_base_fk; -- INNER JOIN IS DEFAULT JOIN
--SELECT * FROM homes JOIN avengers ON home_base_fk = home_base;

-- full outer join
SELECT * FROM avengers FULL OUTER JOIN homes ON home_base = home_base_fk;


-- left outer join
SELECT * FROM avengers LEFT JOIN homes ON home_base = home_base_fk;
SELECT * FROM homes LEFT JOIN avengers ON home_base_fk = home_base;

-- right outer join
SELECT * FROM avengers RIGHT JOIN homes ON home_base = home_base_fk;
SELECT * FROM homes RIGHT JOIN avengers ON home_base_fk = home_base;


-- set operations

-- UNION
-- all distinct rows from each query, no duplicates
SELECT home_base_fk FROM avengers UNION SELECT home_base FROM homes;

-- union ALL 
SELECT home_base_fk FROM avengers UNION ALL SELECT home_base FROM homes;

-- intersect
SELECT home_base_fk FROM avengers INTERSECT SELECT home_base FROM homes;

-- except
SELECT home_base FROM homes EXCEPT SELECT home_base_fk FROM avengers;


-- Transaction and TCL
-- set up

ALTER TABLE avengers ADD COLUMN active boolean;
ALTER TABLE avengers ALTER COLUMN active SET DEFAULT TRUE;

TRUNCATE TABLE avengers;

-- starting a new transaction
BEGIN;

INSERT INTO avengers(hero_name, hero_power, f_name, l_name, home_base_fk)
VALUES ('Hulk', 'mad', 'Bruce', 'Banner', 'Avengers Tower'),
	   ('Black Panther', 'with cat-like tread', 'T''chala', 'Wakanda', 'Avengers Tower');
	   
UPDATE avengers SET active = FALSE WHERE hero_name= 'Hulk' OR hero_name='Black Panther';

COMMIT; -- TRANSACTION ends;

SELECT * FROM avengers;
-- until you commit or rollback everything else you try to do in the database will be BLOCKED 
-- until you either finish the transaction or undo its changes.
-- rollback doesn't happen implicitly, call it in order to undo changes if a transaction failed.