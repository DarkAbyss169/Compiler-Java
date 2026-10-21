# Scanner Compiler Project (Phase 1)

This project is the first phase of my school compiler design assignment, which focuses on building a Scanner (also called a Lexical Analyzer) in Java using Maven.

In a compiler, the scanner reads raw source code character by character, skips unwanted characters like spaces and comments, and groups the characters into meaningful tokens (such as keywords, identifiers, numbers, operators, delimiters, and strings). These tokens are then passed to the parser in the next phase.

## Project Setup & Tools Used

As stated in the assignment requirements, we selected the following tools for the project:

- **Programming Language**: Java (Version 21). Java 21 was chosen because it is a Long-Term Support (LTS) release, stable, modern, and widely compatible across all systems.
- **Build Tool**: Apache Maven. Maven was chosen because it has a standard folder structure, handles dependencies easily, and makes building and testing predictable across any machine using the Maven wrapper (`mvnw`).
- **Code Editor**: Visual Studio Code (VS Code) with the Extension Pack for Java.

## Key Responsibilities of the Scanner

Our scanner is responsible for:
1. Skipping whitespace (spaces, tabs, newlines).
2. Skipping single-line comments (`// ...`) and multi-line comments (`/* ... */`).
3. Tracking line numbers so tokens can report where they appear for error handling.
4. Recognizing keywords vs regular variable names (identifiers).
5. Reading multi-digit numbers, including decimal numbers (like `3.14`).
6. Handling string literals inside double quotes (`"..."`).
7. Distinguishing two-character operators (`==`, `!=`, `<=`, `>=`, `&&`, `||`, `++`, `--`) from single-character operators (`+`, `-`, `*`, `/`, `=`).
8. Identifying delimiters and punctuation symbols (`;`, `,`, `(`, `)`, `{`, `}`, `[`, `]`).
9. Tagging unknown characters as `UNKNOWN`.
10. Adding an `EOF` (End of File) token at the end of the input.

## Project Structure

The project follows the standard Maven layout:

```
democompiler/
├── pom.xml                                              # Maven build configuration
├── README.md                                            # Project documentation
├── errors.md                                            # Build and error log
├── src/
│   ├── main/java/com/example/compiler/democompiler/
│   │   ├── Token.java                                  # Token class and TokenType enum
│   │   ├── Scanner.java                                # Core lexer that reads characters and produces tokens
│   │   └── DemocompilerApplication.java                # Main entry point to run and test the scanner
│   └── test/java/com/example/compiler/democompiler/
│       └── DemocompilerApplicationTests.java           # Unit tests for the scanner
```

### Explanation of the Files:
- **`Token.java`**: Represents each token found. Stores the `type` (from `TokenType`), the `value` (lexeme text), and the `line` number.
- **`Scanner.java`**: The main lexical analysis logic. Uses a pointer `pos` and `currentChar` to iterate through the input string with helper methods like `advance()`, `peek()`, `skipWhitespace()`, and `tokenize()`.
- **`DemocompilerApplication.java`**: Contains the `main` method that creates a `Scanner` instance and prints the scanned tokens to the console.
- **`DemocompilerApplicationTests.java`**: Standard JUnit 5 test file located in `src/test` to verify that scanning works during automated builds.

## Token Types Supported

| Token Type | Description | Examples |
| :--- | :--- | :--- |
| `KEYWORD` | Reserved words in the language | `int`, `float`, `char`, `boolean`, `if`, `else`, `while`, `for`, `return`, `print` |
| `IDENTIFIER` | Names for variables and functions | `x`, `count`, `score`, `total` |
| `NUMBER` | Integer and decimal values | `10`, `42`, `2.5`, `3.14` |
| `STRING` | Text literals inside quotes | `"hello"`, `"x is greater"` |
| `OPERATOR` | Math, comparison, and logic | `+`, `-`, `*`, `/`, `=`, `==`, `!=`, `<=`, `>=`, `&&`, `||` |
| `DELIMITER` | Punctuation and brackets | `;`, `,`, `(`, `)`, `{`, `}`, `[`, `]` |
| `UNKNOWN` | Unrecognized characters | Characters not part of the language syntax |
| `EOF` | End of File | Placed at the very end to signal parsing can stop |

## How to Build and Run the Project

You do not need to install Maven on your computer because the Maven Wrapper is already included in the repository.

### 1. Compile the code
In your terminal (PowerShell or Command Prompt in the project folder), run:
```bash
.\mvnw.cmd clean compile
```

### 2. Run the automated tests
To run the JUnit tests in `src/test`:
```bash
.\mvnw.cmd test
```

### 3. Run the Scanner directly
To run the scanner and print the tokens to the console, run:
```bash
.\mvnw.cmd compile exec:java
```
Or open `DemocompilerApplication.java` in VS Code and click **Run**.

## Sample Output

When the scanner runs on a sample snippet like:
```java
int x = 10;
float y = 2.5;
if (x >= 5) {
    print("x is greater");
} else {
    x = x + 1;
}
```

The output printed to the terminal is:
```text
Tokens:
[KEYWORD : "int" | line 2]
[IDENTIFIER : "x" | line 2]
[OPERATOR : "=" | line 2]
[NUMBER : "10" | line 2]
[DELIMITER : ";" | line 2]
[KEYWORD : "float" | line 3]
[IDENTIFIER : "y" | line 3]
[OPERATOR : "=" | line 3]
[NUMBER : "2.5" | line 3]
[DELIMITER : ";" | line 3]
[KEYWORD : "if" | line 4]
[DELIMITER : "(" | line 4]
[IDENTIFIER : "x" | line 4]
[OPERATOR : ">=" | line 4]
[NUMBER : "5" | line 4]
[DELIMITER : ")" | line 4]
[DELIMITER : "{" | line 4]
[KEYWORD : "print" | line 5]
[DELIMITER : "(" | line 5]
[STRING : "x is greater" | line 5]
[DELIMITER : ")" | line 5]
[DELIMITER : ";" | line 5]
[DELIMITER : "}" | line 6]
[KEYWORD : "else" | line 6]
[DELIMITER : "{" | line 6]
[IDENTIFIER : "x" | line 7]
[OPERATOR : "=" | line 7]
[IDENTIFIER : "x" | line 7]
[OPERATOR : "+" | line 7]
[NUMBER : "1" | line 7]
[DELIMITER : ";" | line 7]
[DELIMITER : "}" | line 8]
[EOF : "EOF" | line 8]
```

## Next Phase (Phase 2)
In the next phase of the compiler, these tokens will be fed into a Parser (Syntax Analyzer) to construct a parse tree / Abstract Syntax Tree (AST) according to the grammar rules of the language.
