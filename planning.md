# Scanner Compiler Project Plan

## Project Overview

This school project is a simple scanner (also called a lexical analyzer). It reads source code and groups characters into tokens that are easier for a compiler to work with. It also skips spaces and comments. At the moment, the project focuses on scanning; it does not yet include a parser.

The project uses Java and Maven and was developed in Visual Studio Code with Java extensions.

## Token Specifications

The scanner recognizes keywords such as int, if, return,string , etc.
 Identifiers are names for things like variables, such as x or y . Numbers can be whole numbers like 10 or decimals(float or doable) like 2.5. Strings are text inside double quotes, such as "hello".

Operators are symbols for actions or comparisons, such as + , =, ==, and &&. Delimiters group or separate code, such as (, ), {, }, and ;. If the scanner finds a character it does not recognize, it marks it as UNKNOWN . When it reaches the end of the input, it adds an `EOF` token.

Spaces, tabs, newlines, and comments are skipped rather than returned as tokens. Each token records its line number.

## Architecture & Component Design

1. *DemocompilerApplication* — Starts the example program, sends sample source code to the scanner, and prints the tokens.
2. *Scanner*— Reads the source code character by character, recognizes the token categories above, skips whitespace and comments, and returns the tokens in a list.
3. *Token*— Stores a token's type, value, and line number.
4. *TokenType* — The enum inside `Token` that names the token categories used by the scanner.

The basic flow is: source code → scanner → list of tokens → printed output.

## Key Responsibilities

- Recognize keywords, identifiers, numbers, strings, operators, and delimiters.
- Skip whitespace and comments.
- Keep track of line numbers for tokens.
- Mark unrecognized characters as `UNKNOWN` and add `EOF` at the end.

## Implementation Phases

1. **PHASE 1** Setting  up the project , choosing Java v25 and Maven, and create the project structure.
2. **PHASE 2** Define tokens, add the token categories and the Token  class to store each token's type, value, and line number.
3. **PAHSE 3** Building the scanner .To read the input and recognize words, numbers, strings, operators, and delimiters.
4. **PHASE 4** Handle ignored and unexpected input. Skipping whitespace and comments, mark unrecognized characters as UNKNOWN , and finish with EOF.
5. **PHASE 5** Try and test the scanner. Running the sample program and check that the scanner returns the expected tokens.
