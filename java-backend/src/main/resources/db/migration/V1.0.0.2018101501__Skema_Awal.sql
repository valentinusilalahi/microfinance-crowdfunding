CREATE TABLE application_info (
	id VARCHAR(36),
	name VARCHAR(255) NOT NULL,
	value VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE fund_provider (
	id VARCHAR(36),
	salution VARCHAR(5),
	fullname VARCHAR(5),
	email VARCHAR(50),
	phone VARCHAR(15),
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ('id'),
	UNIQUE KEY 'email'
);