CREATE TABLE application_client(
	id VARCHAR(50) PRIMARY KEY,
	nama VARCHAR(255) UNIQUE,
	created_date BIGINT,
	created_by VARCHAR(255),
	last_modified_date BIGINT,
	last_modified_by VARCHAR(255)
);