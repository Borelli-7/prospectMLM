CREATE TABLE IF NOT EXISTS notifications (
	notif_id serial PRIMARY KEY,
	created_date timestamp,
	title varchar(255) NOT NULL,
	message text NOT NULL,
	user_name varchar(50)
);

CREATE TABLE IF NOT EXISTS addresses (
	address_id serial PRIMARY KEY,
	street varchar(50),
	city varchar(50),
	state varchar(50),
	zip_code varchar(50),
	residence_country varchar(50) NOT NULL,
	nationality varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS persons(
	person_id serial PRIMARY KEY,
	first_name varchar(50) NOT NULL,
	last_name varchar(50),
	phone_number varchar(50) NOT NULL,
	email varchar(50),
	activity varchar(50),
	interest varchar(50),
	description text,
	user_name varchar(50),
	address_id integer REFERENCES addresses (address_id)
);

CREATE TABLE IF NOT EXISTS prospects(
	prospect_id serial PRIMARY KEY,
	influence_circle varchar(25) NOT NULL,
	converted boolean,
	user_name varchar(50),
	person_id integer REFERENCES persons (person_id)
);

CREATE TABLE IF NOT EXISTS followings(
	follow_id serial PRIMARY KEY,
	follow_number integer NOT NULL,
	created_date timestamp,
	validated_date timestamp,
	next_follow_date timestamp,
	validated boolean,
	user_name varchar(50),
	prospect_id integer REFERENCES prospects (prospect_id)
);

CREATE TABLE IF NOT EXISTS prospectings(
	prospecting_id serial PRIMARY KEY,
	prospecting_type varchar(25) NOT NULL,
	created_date timestamp,
	first_prospecting boolean,
	validated boolean,
	validated_date timestamp,
	prospect_id integer REFERENCES prospects (prospect_id)
);
