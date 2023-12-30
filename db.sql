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




-----------------------------------------------------------------------------------


CREATE TABLE CORRECT_ANSWERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    answer_a_correct BOOLEAN,
    answer_b_correct BOOLEAN,
    answer_c_correct BOOLEAN,
    answer_d_correct BOOLEAN,
    answer_e_correct BOOLEAN,
    answer_f_correct BOOLEAN
);



CREATE TABLE ANSWERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    answer_a VARCHAR(255),
    answer_b VARCHAR(255),
    answer_c VARCHAR(255),
    answer_d VARCHAR(255),
    answer_e VARCHAR(255),
    answer_f VARCHAR(255)
);

CREATE TABLE TAGS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);




CREATE TABLE QUESTION (
    id INT PRIMARY KEY,
    question VARCHAR(255),
    description TEXT,
    multiple_correct_answers BOOLEAN,
    correct_answer VARCHAR(255),
    explanation TEXT,
    tip TEXT,
    category VARCHAR(255),
    difficulty VARCHAR(50),
    answers_id INT,
    correct_answers_id INT,
    tag_id INT,
    FOREIGN KEY (answers_id) REFERENCES ANSWERS(id),
    FOREIGN KEY (correct_answers_id) REFERENCES CORRECT_ANSWERS(id),
    FOREIGN KEY (tag_id) REFERENCES TAGS(id)
);


CREATE TABLE Quiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    Q1 INT,
    Q2 INT,
    Q3 INT,
    Q4 INT,
    Q5 INT,
    FOREIGN KEY (Q1) REFERENCES QUESTION(id),
    FOREIGN KEY (Q2) REFERENCES QUESTION(id),
    FOREIGN KEY (Q3) REFERENCES QUESTION(id),
    FOREIGN KEY (Q4) REFERENCES QUESTION(id),
    FOREIGN KEY (Q5) REFERENCES QUESTION(id)
);