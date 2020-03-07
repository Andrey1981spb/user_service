DROP SEQUENCE IF EXISTS token_seq;

CREATE SEQUENCE userseq START WITH 1;
CREATE SEQUENCE token_seq START WITH 1;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('userseq'),
    name             VARCHAR                 NOT NULL,
    email            VARCHAR                 NOT NULL,
    phone            VARCHAR                 NOT NULL,
    locale           VARCHAR,
    short_code       VARCHAR,
    email_valid      BOOLEAN
);

CREATE UNIQUE INDEX users_unique_phone_idx ON users (phone);

CREATE TABLE tokens
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('token_seq'),
    token             VARCHAR                 NOT NULL,
    created_date      timestamp               NOT NULL,
    expiry_date       timestamp               NOT NULL,
    FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);