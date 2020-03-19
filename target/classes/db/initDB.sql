DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS tokens;
DROP TABLE IF EXISTS trackers;

DROP SEQUENCE IF EXISTS userseq;
DROP SEQUENCE IF EXISTS token_seq CASCADE;
DROP SEQUENCE IF EXISTS tracker_seq CASCADE;

CREATE SEQUENCE userseq START WITH 1;
CREATE SEQUENCE token_seq START WITH 1;
CREATE SEQUENCE tracker_seq START WITH 1;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('userseq'),
    name             VARCHAR                 NOT NULL,
    email            VARCHAR                 NOT NULL,
    phone            VARCHAR                 NOT NULL,
    locale           VARCHAR,
    short_code       VARCHAR,
    email_valid      BOOLEAN,
    city             VARCHAR                 NOT NULL
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

CREATE TABLE trackers
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('tracker_seq'),
    name              VARCHAR                 NOT NULL,
    value             VARCHAR                 NOT NULL,
    user_id           INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);