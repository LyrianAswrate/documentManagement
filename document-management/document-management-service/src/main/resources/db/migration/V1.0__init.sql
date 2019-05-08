CREATE TABLE users(
	id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE documents(
	id BIGINT NOT NULL AUTO_INCREMENT,
    regnumber VARCHAR(20) NOT NULL,
    filename VARCHAR(255) NOT NULL,
    content LONGBLOB NOT NULL,
    content_size BIGINT NOT NULL,
    description TEXT,
    create_user_id BIGINT NOT NULL REFERENCES users(id),
    create_date DATETIME NOT NULL,
    modify_user_id BIGINT REFERENCES users(id),
    modify_date DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE document_label_xrefs(
	id BIGINT NOT NULL AUTO_INCREMENT,
    document_id BIGINT NOT NULL REFERENCES documents(id),
    label_id BIGINT NOT NULL REFERENCES labels(id),
	PRIMARY KEY (id)
);

CREATE TABLE labels(
	id BIGINT NOT NULL AUTO_INCREMENT, 
    label VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

