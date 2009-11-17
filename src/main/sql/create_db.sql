create database tofucube;

create table users (
  name text PRIMARY KEY,
  hash text NOT NULL
);

create table portfolios (
  id SERIAL PRIMARY KEY,
  username text references users,
  name text NOT NULL
);

create table companies (
  code char(3) PRIMARY KEY,
  name text NOT NULL,
  classification text NOT NULL
);

create table buys (
  id SERIAL,
  portfolio_id integer references portfolios,
  company char(3) references companies,
  quantity integer NOT NULL CHECK (quantity > 0),
  price numeric CHECK (price > 0.0)
);
