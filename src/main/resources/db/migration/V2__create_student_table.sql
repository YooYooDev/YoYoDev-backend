
CREATE TABLE student(
	id      SERIAL,
	first_name VARCHAR,
	middle_name VARCHAR,
	last_name VARCHAR,
	role VARCHAR,
	school_id INTEGER REFERENCES school(id),
	p_email VARCHAR,
	s_email VARCHAR,
	parent_mobile1 VARCHAR,
	parent_mobile2 VARCHAR,
	password VARCHAR,
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);


	