--! Create db

CREATE DATABASE JavaQuiz;

USE quiz;

--! Create the  tables

CREATE TABLE Persons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE Professors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    personId INT NOT NULL,
    FOREIGN KEY (personId) REFERENCES Persons(id)
);

CREATE TABLE Students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    personId INT NOT NULL,
    FOREIGN KEY (personId) REFERENCES Persons(id)
);


CREATE TABLE Quizs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    professorId INT NOT NULL,
    FOREIGN KEY (professorId) REFERENCES Professors(id)
);


