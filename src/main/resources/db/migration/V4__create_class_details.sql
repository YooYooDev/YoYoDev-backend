CREATE TABLE grade(
	id      SERIAL,
	name VARCHAR,
	PRIMARY KEY (id)
	);

	

INSERT INTO grade(name)
VALUES ('NURSERY'),('L.K.G'),('U.K.G');	
	
ALTER TABLE student 
ADD COLUMN grade_id INTEGER;