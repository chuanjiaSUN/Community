CREATE TABLE USER(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	account_id VARCHAR(50),
	NAME VARCHAR(50),
	token CHAR(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	avatar_url VARCHAR (100) NULL,
	bio varchar(256) NULL
);