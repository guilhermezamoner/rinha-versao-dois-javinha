-- tables
CREATE TABLE users (
    id INTEGER NOT NULL,
    balance INTEGER NOT NULL,
    user_limit INTEGER NOT NULL
);

CREATE TABLE transactions (
    id integer NOT NULL,
    user_id bigint NOT NULL,
    amount integer NOT NULL,
    description varchar(10) NOT NULL,
    transaction_type varchar(1) NOT NULL,
    transaction_date timestamp NOT NULL
);


ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY (id);
ALTER TABLE transactions ADD CONSTRAINT transactions_pk PRIMARY KEY (id);

-- sequences
CREATE SEQUENCE transactions_id_seq START WITH 1;
CREATE SEQUENCE users_id_seq START WITH 1;

-- indexes
CREATE INDEX users_id_idx ON users (id);
CREATE INDEX transactions_user_idx ON transactions (user_id);
CREATE INDEX transactions_id_idx ON transactions (id);
CREATE INDEX transactions_date_idx ON transactions (transaction_date);

-- data
INSERT INTO users (id, user_limit, balance) VALUES (nextval('users_id_seq'), 100000, 0);
INSERT INTO users (id, user_limit, balance) VALUES (nextval('users_id_seq'), 80000, 0);
INSERT INTO users (id, user_limit, balance) VALUES (nextval('users_id_seq'), 1000000, 0);
INSERT INTO users (id, user_limit, balance) VALUES (nextval('users_id_seq'), 10000000, 0);
INSERT INTO users (id, user_limit, balance) VALUES (nextval('users_id_seq'), 500000, 0);
