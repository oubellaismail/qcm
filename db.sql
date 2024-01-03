--! Create db

CREATE DATABASE quiz-app;

USE quiz-app;

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



CREATE TABLE USERS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    level INT DEFAULT 1
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


CREATE TABLE QUIZ (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USERS(id)
);

CREATE TABLE question_quiz (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    quiz_id INT,
    FOREIGN KEY (question_id) REFERENCES QUESTION(id),
    FOREIGN KEY (quiz_id) REFERENCES QUIZ(id)
);


