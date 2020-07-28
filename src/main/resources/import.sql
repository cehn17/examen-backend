/*create database db_examen;
use db_examen;
CREATE TABLE users (
	id bigint not null auto_increment, 
	email varchar(255),
	first_name varchar(255), 
	last_name varchar(255),
	primary key(id)
);

CREATE TABLE loans (
	id bigint not null auto_increment, 
	total double, 
	user_id bigint,
	primary key(id),
	foreign key(user_id) references users(id)
);*/

/* Populate Users */

INSERT INTO users (email, first_Name, last_Name) VALUES('test@app.com.ar', 'Pepe', 'Argento');
INSERT INTO users (email, first_Name, last_Name) VALUES('john.smith@gmail.com','John', 'Smith');
INSERT INTO users (email, first_Name, last_Name) VALUES('joe.bloggs@gmail.com','Joe', 'Bloggs');
INSERT INTO users (email, first_Name, last_Name) VALUES('john.stiles@gmail.com','John', 'Stiles');
INSERT INTO users (email, first_Name, last_Name) VALUES('stiles.roe@gmail.com','Richard', 'Roe');

/* Populate Loans */

INSERT INTO loans (total,user_id) VALUES(25000.00,1);

INSERT INTO loans (total,user_id) VALUES(65120.75,1);

INSERT INTO loans (total,user_id) VALUES(100000.00,2);

INSERT INTO loans (total,user_id) VALUES(23000.75,2);
INSERT INTO loans (total,user_id) VALUES(25000.00,3);

INSERT INTO loans (total,user_id) VALUES(65120.75,3);

INSERT INTO loans (total,user_id) VALUES(100000.00,4);

INSERT INTO loans (total,user_id) VALUES(23000.75,4);
INSERT INTO loans (total,user_id) VALUES(25000.00,5);

INSERT INTO loans (total,user_id) VALUES(65120.75,1);

INSERT INTO loans (total,user_id) VALUES(100000.00,2);

INSERT INTO loans (total,user_id) VALUES(23000.75,2);
INSERT INTO loans (total,user_id) VALUES(25000.00,1);

INSERT INTO loans (total,user_id) VALUES(65120.75,1);

INSERT INTO loans (total,user_id) VALUES(100000.00,2);

INSERT INTO loans (total,user_id) VALUES(23000.75,2);