package com.example.compiler.democompiler;

import java.util.ArrayList;
import java.util.List;

// Simple scanner class  that breaks source code string into tokens
public class Scanner {
    private String input;
    private int pos = 0;        // current index in the input
    private char currentChar;   // current character we are inspecting
    private int line = 1;       // track line number for errors
     
    //This is a constructor for the scanner class, it takes a string input as a parameter which represents the source code to be scanned and tokenized. It initializes the input field with the provided string, sets the current character to the first character of the input (if available), and prepares the scanner for tokenization.
    public Scanner(String input) {
        this.input = input;
        if (input != null && !input.isEmpty()) {
            this.currentChar = input.charAt(0);
        } else {
            this.currentChar = '\0';
        }
    }

    // It advances or moves the current position in the input string by one character
    private void advance() {
        if (currentChar == '\n') {
            line++;
        }
        pos++;
        if (pos < input.length()) {
            currentChar = input.charAt(pos);
        } else {
            currentChar = '\0'; // reached end of input
        }
    }

    // Look at next character without advancing the pointer
    private char peek() {
        if (pos + 1 < input.length()) {
            return input.charAt(pos + 1);
        }
        return '\0';
    }

    // Skip whitespace such as spaces, tabs, and newlines
    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    // Check if a word is one of our reserved keywords
    private boolean isKeyword(String word) {
        String[] keywords = {
            "int", "float", "char", "boolean", "void",
            "if", "else", "while", "for", "return",
            "true", "false", "print", "println"
        };
        for (String k : keywords) {
            if (k.equals(word)) {
                return true;
            }
        }
        return false;
    }

    // This the main method of the scanner class that performs the tokenization process. It iterates through the input string, identifies different types of tokens (keywords, identifiers, numbers, operators, delimiters, strings, etc.) and creates Token objects for each recognized token. The method returns a list of tokens representing the entire input source code.
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {

            // Skip spaces and tabs
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            // Single line comments
            if (currentChar == '/' && peek() == '/') {
                advance();
                advance();
                while (currentChar != '\0' && currentChar != '\n') {
                    advance();
                }
                continue;
            }

            // Block comments /* ... */
            if (currentChar == '/' && peek() == '*') {
                advance();
                advance();
                while (currentChar != '\0') {
                    if (currentChar == '*' && peek() == '/') {
                        advance();
                        advance();
                        break;
                    }
                    advance();
                }
                continue;
            }

            // It is the identifies and keywords. It checks if the current character is a letter or an underscore (which can start an identifier). It then continues to read characters as long as they are letters, digits, or underscores, building the complete identifier or keyword. After forming the word, it checks if it is a reserved keyword and creates the appropriate Token object (either KEYWORD or IDENTIFIER) and adds it to the tokens list.
            if (Character.isLetter(currentChar) || currentChar == '_') {
                StringBuilder sb = new StringBuilder();
                while (currentChar != '\0' && (Character.isLetterOrDigit(currentChar) || currentChar == '_')) {
                    sb.append(currentChar);
                    advance();
                }
                String word = sb.toString();

                if (isKeyword(word)) {
                    tokens.add(new Token(Token.TokenType.KEYWORD, word, line));
                } else {
                    tokens.add(new Token(Token.TokenType.IDENTIFIER, word, line));
                }
                continue;
            }

            // It is the numbers. It checks if the current character is a digit. If so, it reads consecutive digits to form a complete number. It also checks for decimal points to handle floating-point numbers. Once the number is fully read, it creates a NUMBER Token and adds it to the tokens list.
            if (Character.isDigit(currentChar)) {
                StringBuilder sb = new StringBuilder();
                while (currentChar != '\0' && Character.isDigit(currentChar)) {
                    sb.append(currentChar);
                    advance();
                }

                // Check if there is a decimal point like double etc3.14
                if (currentChar == '.' && Character.isDigit(peek())) {
                    sb.append(currentChar);
                    advance();
                    while (currentChar != '\0' && Character.isDigit(currentChar)) {
                        sb.append(currentChar);
                        advance();
                    }
                }

                tokens.add(new Token(Token.TokenType.NUMBER, sb.toString(), line));
                continue;
            }

            // This is the string literals. It checks if the current character is a double quote ("). If so, it reads characters until it finds the closing double quote, handling escape sequences (like \") along the way. The complete string literal is then used to create a STRING Token, which is added to the tokens list.
            if (currentChar == '"') {
                StringBuilder sb = new StringBuilder();
                advance(); // skip starting quote
                while (currentChar != '\0' && currentChar != '"') {
                    if (currentChar == '\\' && peek() != '\0') {
                        advance(); // skip escape backslash
                    }
                    sb.append(currentChar);
                    advance();
                }
                if (currentChar == '"') {
                    advance(); // skip closing quote
                }
                tokens.add(new Token(Token.TokenType.STRING, sb.toString(), line));
                continue;
            }

            // Two-character operators.It must check for two-character operators (like ==, !=, <=, >=, &&, ||, ++, --) before single-character operators. If a two-character operator is found, it creates an OPERATOR Token and adds it to the tokens list.
            if (currentChar == '=' && peek() == '=') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "==", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '!' && peek() == '=') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "!=", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '<' && peek() == '=') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "<=", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '>' && peek() == '=') {
                tokens.add(new Token(Token.TokenType.OPERATOR, ">=", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '&' && peek() == '&') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "&&", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '|' && peek() == '|') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "||", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '+' && peek() == '+') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "++", line));
                advance(); advance();
                continue;
            }
            if (currentChar == '-' && peek() == '-') {
                tokens.add(new Token(Token.TokenType.OPERATOR, "--", line));
                advance(); advance();
                continue;
            }

            // Single-character operators
            if ("+-*/%=<>!".indexOf(currentChar) != -1) {
                tokens.add(new Token(Token.TokenType.OPERATOR, String.valueOf(currentChar), line));
                advance();
                continue;
            }

            // Separators and delimiters. It checks if the current character is one of the recognized delimiters (like parentheses, braces, brackets, commas, semicolons). If so, it creates a DELIMITER Token and adds it to the tokens list.
            if ("();{},[]".indexOf(currentChar) != -1) {
                tokens.add(new Token(Token.TokenType.DELIMITER, String.valueOf(currentChar), line));
                advance();
                continue;
            }

            // it used to handle any unrecognized characters. If the current character does not match any of the expected token types, it creates an UNKNOWN Token with the character's value and adds it to the tokens list. This can be useful for error handling or reporting unexpected input.
            tokens.add(new Token(Token.TokenType.UNKNOWN, String.valueOf(currentChar), line));
            advance();
        }

        // this is used to mark the end of the input. After processing the entire input string, it adds an EOF (End Of File) Token to the tokens list. This token indicate there are no more token to read.
        tokens.add(new Token(Token.TokenType.EOF, "EOF", line));
        return tokens;
    }
}
