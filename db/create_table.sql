DROP TABLE IF EXISTS Account, accounts;

CREATE TABLE accounts (
	acc_id SERIAL PRIMARY KEY,
	acc_name text,
	acc_description text
);
