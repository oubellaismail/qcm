--! Create db

CREATE DATABASE IF NOT EXISTS JavaQuiz;



USE quiz;

--! Create the 'Persons' table
CREATE TABLE IF NOT EXISTS Persons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);


