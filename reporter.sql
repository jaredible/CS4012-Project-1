CREATE DATABASE IF NOT EXISTS reporter;

USE reporter;

CREATE TABLE IF NOT EXISTS assignment (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title varchar(20) NOT NULL,
  author varchar(20) NOT NULL,
  due_date timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS question (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  content varchar(500) NOT NULL
);