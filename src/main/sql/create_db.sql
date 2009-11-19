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
  day date NOT NULL,
  quantity integer NOT NULL CHECK (quantity > 0),
  price numeric NOT NULL CHECK (price > 0.0),
  brokerage numeric NOT NULL CHECK (brokerage >= 0.0)
);

create table company_history (
  code char(3) references companies,
  day date NOT NULL,
  closing_price numeric NOT NULL CHECK (closing_price >= 0.0),
  UNIQUE(code, day)
);

