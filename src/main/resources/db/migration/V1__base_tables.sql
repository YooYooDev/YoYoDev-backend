
CREATE TABLE school(
	id  SERIAL,
	name   VARCHAR,
	code   INTEGER,
	owner_name VARCHAR,
	address1 VARCHAR,
	post VARCHAR,
	pin VARCHAR,
	state VARCHAR,
	country VARCHAR,
	contact_person VARCHAR,
	owner_mobile VARCHAR,
	email_id VARCHAR,
	active INTEGER,
	registration_name VARCHAR,
	enable_fees INTEGER,
	enable_attendance INTEGER,
	enable_printed_worksheet INTEGER,
	created_at TIMESTAMP,
	updatedd_at TIMESTAMP,
	deleted VARCHAR,
	PRIMARY KEY (id)
	);

CREATE TABLE role(
	id SERIAL ,
	name VARCHAR,
	PRIMARY KEY (id)
	);

	
CREATE TABLE cred_manager(
	id SERIAL ,
	user_name VARCHAR NOT NULL,
	email VARCHAR DEFAULT NULL,
	password VARCHAR DEFAULT NULL,
	school_id INTEGER REFERENCES school(id),
	role_id  INTEGER REFERENCES role(id),
	PRIMARY KEY (id)
	);
	
--	create seed data for school and role

INSERT INTO school(name,code,owner_name,address1,post,pin,state,country,contact_person,owner_mobile,email_id,active,registration_name,enable_fees,enable_attendance,enable_printed_worksheet,created_at,updatedd_at,deleted)
VALUES('Demo SChool',123456,'Admin','Bangalore','Bangalore','560068','Karnataka','India','Admin','89099009','admin@email.com',1,'asdf',1,1,1,current_timestamp,current_timestamp,'N');


INSERT INTO role(name)
VALUES ('SUPERADMIN'),('YOYOADMIN'),('SCHOOLOWNER'),('TEACHER');

INSERT INTO cred_manager(user_name,email,password,school_id,role_id)
VALUES('TestAdmin','test@admin.com','Welcome@123',1,1);

