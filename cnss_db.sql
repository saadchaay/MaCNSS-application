
-- database name is  "cnss_app" , Make sure to your database has the same name  !!!!



CREATE TABLE  refundableMedicals (
id serial PRIMARY KEY,
codeBarre numeric(15,0) UNIQUE NOT NULL,
refPrice numeric(7,2) NOT NULL
);

INSERT INTO refundableMedicals (codeBarre, refPrice) VALUES
(6118000010050, 16.70),
(6118001170388, 30.71), 
(6118000130796, 26.30),
(6118000260202, 120.17),
(6118000260189, 53.33);

CREATE TYPE docType AS ENUM ('ANALYSE', 'RADIO', 'SCANNER');

CREATE TABLE refundableDocs (
id serial PRIMARY KEY,
type docType NOT NULL,
refPercentage numeric(7,2) NOT NULL
);

INSERT INTO refundableDocs (type, refPercentage) VALUES
('ANALYSE', 73),
('RADIO', 80),
('SCANNER', 92);


CREATE TABLE patients (
    id serial PRIMARY KEY,
    matricule numeric(9,0) UNIQUE NOT NULL ,
    firstName VARCHAR(200) NOT NULL ,
    lastName varchar(200) NOT NULL ,
    email varchar(200) NOT NULL ,
    password varchar(200) not null
);


INSERT INTO patients (matricule, firstname, lastname, email, password) VALUES
    (224465462, 'saad', 'chaay', 'saadchaay@gmail.com', 'saad1234'),
    (224211946, 'otmane', 'moubtahij', 'o.moubtahij@gmail.com', 'otmane1234'),
    (284965521, 'brahim', 'benJ', 'benJBrah@gmail.com', 'benJ1234'),
    (210965780, 'Reda', 'LAGHRISSI', 'laghrissi.reda@gmail.com', 'reda1234');

CREATE TYPE role as ENUM ('agent', 'admin');

create table users
(
    id  serial not null
        constraint users_primary_key
        primary key,
    email    varchar(200)  UNIQUE    not null,
    password varchar(200)            not null,
    role     role    default 'agent' not null,
    verified boolean default false
);

INSERT INTO users(email,password,role) VALUES
("test@gmail.com","dsfsdfsdsdf","admin"),
("agent2@gmail.com","dsfsdfsdsdf","agent"),
("agent3@gmail.com","dsfsdfsdsdf","agent"),
("agent5@gmail.com","dsfsdfsdsdf","agent"),
("agent4@gmail.com","dsfsdfsdsdf","agent");

CREATE TYPE statusType as ENUM ('PENDING', 'REFUSED', 'VALIDATED');

create table dossiers
(
    id  serial not null
        constraint dossiers_primary_key
            primary key,
    codeDossier numeric(5) UNIQUE not null ,
    matricule numeric(9) not null,
    appliedDate DATE NOT NULL DEFAULT CURRENT_DATE,
    status   statustype default 'PENDING' not null,
    montantRem numeric(7,2) default 0.00 not null
);


create table paipers
(
    id  serial not null
        constraint paipers_primary_key
            primary key,
    dossierID int not null ,
    price numeric(7,2) not null
);

alter table paipers
    add constraint dossier_papier
        foreign key (dossierID) references dossiers (id);

alter table dossiers
    add constraint patients_dossiers
        foreign key (matricule) references patients (matricule);


CREATE TABLE documents (
    id serial PRIMARY KEY not null ,
    type doctype not null
) inherits (paipers);


CREATE TABLE medicals (
    id serial PRIMARY KEY not null ,
    codeBarre numeric(15) not null
) inherits (paipers);

