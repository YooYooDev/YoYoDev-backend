
CREATE TABLE topic(
    id SERIAL,
	name VARCHAR,
	created_date TIMESTAMP,
	updated_date TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE subject_master(
    id SERIAL,
	subject_name VARCHAR,
	created_date TIMESTAMP,
	updated_date TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE category_master(
    id SERIAL,
	category_name VARCHAR,
	created_date TIMESTAMP,
	updated_date TIMESTAMP,
	PRIMARY KEY (id)
);


CREATE TABLE topic_video(
    id SERIAL,
    topic_id INTEGER,
    video_link VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topic (id)
);

CREATE TABLE topic_quiz(
    id SERIAL,
    topic_id INTEGER,
    quiz_link VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topic (id)
);

CREATE TABLE topic_worksheet(
    id SERIAL,
    topic_id INTEGER,
    worksheet_link VARCHAR,
    PRIMARY KEY (id),
    FOREIGN KEY (topic_id) REFERENCES topic (id)
);




CREATE TABLE topic_subject(
	topic_id INTEGER,
    subject_id INTEGER,
    FOREIGN KEY (topic_id) REFERENCES topic (id),
	FOREIGN KEY (subject_id) REFERENCES subject_master (id)
);

CREATE TABLE topic_catagory(
	topic_id INTEGER,
    category_id INTEGER,
    FOREIGN KEY (topic_id) REFERENCES topic (id),
	FOREIGN KEY (category_id) REFERENCES category_master (id)
);
