CREATE TABLE feedback(
    id SERIAL,
	message TEXT,
	student_id INTEGER,
	created_date TIMESTAMP,
	updated_date TIMESTAMP default current_timestamp,
	PRIMARY KEY (id)
);