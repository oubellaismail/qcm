# Java Quiz Game Mini Project

This mini project is a quiz game that asks questions in the field of computer science. It uses Maven to manage dependencies and connect to a MySQL database. JavaFX is used for styling the application.

## Features

- **User Levels**: Users start at level one when logging in for the first time.
- **Progression**: By answering a certain number of questions at each difficulty level (easy, medium, hard), users can advance to higher levels.
- **Question Types**: The quiz contains questions of varying difficulty levels (easy, medium, hard).
- **User Profile**: Each user has a profile with a username, email, password, and level.

## Project Architecture

The project consists of the following classes:

- **User**: Represents a user with properties such as id, username, email, password, and level.
- **Tag**: Represents a tag for categorizing questions.
- **Quiz**: Represents a quiz with a list of questions and a user ID.
- **Question**: Represents a question with properties such as id, question text, description, answers, correct answers, explanation, tags, category, and difficulty.
- **CorrectAnswers**: Represents the correct answers to a question with properties for each possible answer.
- **Answers**: Represents the available answers to a question with properties for each possible answer.

## Usage

To use this project, follow these steps:

1. **Clone the Repository**: Clone this repository to your local machine.
2. **Build the Project**: Use Maven to build the project and resolve dependencies.
3. **Configure the Database**: Set up a MySQL database and configure the connection details in the project configuration.
4. **Run the Application**: Run the application using JavaFX.

## Contributors

- Oubella Ismail
- Aitbenayad Hamza
