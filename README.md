# Composition of Functions to Mathematical Expression Transformer

Author: Joshua Nwokoye

## Overview

This Java program is designed to transform a composition of functions, 
given as input, into an equivalent mathematical expression while preserving 
the order of operations and removing unnecessary brackets. 
It follows the specified grammar and operator precedence rules.

## Parts of the Program

The program is made up of the following parts:
* Lexer.java
* Parser.java 
* Token.java
* Main.java

## The Lexer.java Class

The Lexer class is responsible for tokenizing the 
input mathematical expression. It uses regular expressions 
to identify different types of tokens, such as numbers, 
operators, whitespace, and parentheses. 
The tokens are then used by the Parser for further processing.

   ```bash
   class Lexer {
    public Lexer(String input) {
        // Constructor takes the input expression and removes comments.
        // ...
    }

    public Token getNextToken() {
        // Method to retrieve the next token in the expression.
        // ...
    }
}
   ```

## The Parser.java Class

## Running the Program

To run the program in merlin server, follow these steps:

1. Ensure you have Java installed on your system. You can download it from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or use a package manager on your platform.

2. Sign in to your merlin sever.

2. Compile the Java source files:

   ```bash
   javac Main.java
   ```
2. To run the compiled source files:

   ```bash
   java Main
   ```
   
