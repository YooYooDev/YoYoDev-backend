CREATE TABLE tickets(
	id      SERIAL,
	subject VARCHAR,
	message TEXT,
	resolution smallint,
	school_id INTEGER REFERENCES school(id),
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);
	
	
CREATE TABLE notifications(
	id      SERIAL,
	header VARCHAR,
	message TEXT,
	school_id INTEGER REFERENCES school(id),
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);	
	
	
CREATE TABLE fees(
	id      SERIAL,
	student_name VARCHAR,
	student_id INTEGER REFERENCES student(id),
	total_bill_amount NUMERIC(8,2),
	paid_bill_amount NUMERIC(8,2),
	pending_amount NUMERIC(8,2),
	school_id INTEGER REFERENCES school(id),
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);	
	
CREATE TABLE attendance(
	id      SERIAL,
	student_id INTEGER REFERENCES student(id),
	school_id INTEGER REFERENCES school(id),
	grade_id  INTEGER REFERENCES grade(id),
    attend_date	 DATE,
	status	INTEGER,
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);	
		
	
	
	
	
	
	

	

