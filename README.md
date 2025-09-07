# Java Othello Game 

## Overview


![Description](othello.gif)


This project is a Java-based Othello game developed as part of the Programming Paradigms module at the University. The game is designed using Java Swing and showcases the application of design patterns and object-oriented programming principles. It features a user-friendly interface with two screens for two players, and an optional AI bot that can play on behalf of the players.

## Features

- **Two-Player Mode**: The game allows two players to compete against each other on separate screens, ensuring an immersive experience.
- **AI Bot**: An optional AI bot is available, which can make moves for one or both players, allowing for single-player gameplay or enhanced AI vs. AI scenarios.
- **Design Patterns**: The game architecture incorporates various design patterns, ensuring a modular, maintainable, and scalable codebase.
- **Object-Oriented Design**: The project follows object-oriented principles, making the code organized, reusable, and easy to understand.

## Project Structure

- **src/**: Contains all the source code files, including the main game logic, UI components, and AI algorithms.
- **res/**: Includes any resource files such as images or configuration files used by the game.
- **README.md**: This file, providing an overview of the project, instructions for running the game, and details on the game's features and design.

## How to Run

1. **Prerequisites**: Ensure you have Java Development Kit (JDK) installed on your machine.

2. **Compile the Game**:
   - Navigate to the project directory.
   - Compile the source code using the following command:
     ```bash
     javac -d bin src/*.java
     ```

3. **Run the Game**:
   - After compilation, run the game using:
     ```bash
     java -cp bin MainClass
     ```
   - Replace `MainClass` with the actual name of your main class.

4. **Gameplay**:
   - The game will start with two screens for the players.
   - Players can choose to play against each other or activate the AI bot to make moves.

## Design and Development

- **User Interface**: Built with Java Swing, the UI is designed to be intuitive and easy to navigate.
- **AI Algorithm**: The AI bot uses strategic algorithms to make decisions, providing a challenging experience for players.
- **Design Patterns**: The project employs design patterns such as Model-View-Controller (MVC) and Singleton to ensure the game is well-structured.

## Acknowledgments

This project was created as part of the  Programming module at the University. Special thanks to the module instructors for their support and guidance throughout the development process.

