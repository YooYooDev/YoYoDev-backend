CREATE TABLE assignments(
    id SERIAL,
	topic_id INTEGER,
	grade_id INTEGER,
    subject_id INTEGER,
    assignment_date DATE,
	created_date TIMESTAMP,
	updated_date TIMESTAMP default current_timestamp,
	CONSTRAINT no_duplicate_tag UNIQUE (topic_id, grade_id, subject_id, assignment_date),
	PRIMARY KEY (id)
);