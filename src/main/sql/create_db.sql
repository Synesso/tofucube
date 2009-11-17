create database tofucube;

create table users (
  name text UNIQUE PRIMARY KEY,
  hash text NOT NULL
);

create table portfolios (
  username text references users,
  name text NOT NULL UNIQUE PRIMARY KEY
);

create table companies (
  code char(3) NOT NULL,
  version integer NOT NULL,
  name text NOT NULL,
  classification text NOT NULL
);