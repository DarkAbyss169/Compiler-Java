package com.example.compiler.democompiler;

public class Token {

    public enum TokenType {
        KEYWORD, // This represents keywords such as "if", "else", "while", etc.
        IDENTIFIER, //This represents variable names such as x,y and function names such as add, subtract, etc.
        NUMBER, //This represents values or numbers such as 1, 2, 5, 9, 3.14, etc.
        OPERATOR, //This represents operators such as +, -, *, /, =, ==, !=, <, > etc.
        DELIMITER, //This represents delimiters such as ;, ,, (, ), {, }, [, ], etc.
        STRING, //This represents string literals such as "hello", "world", etc.
        UNKNOWN, //This appears when something is not recognized by the scanner. It can be used to handle errors or unexpected input.
        EOF //This is used to mark the end of an input
    }
    
    private TokenType type; //This token shows the type of the token 
    private String value; //This token shows the value of the token
    private int line; //This token shows the line number where the token was found in the source code


    //This is a constructor for the token class.It takes three parameters such as type of token ,value of token and the line number where the token was found
    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    //This is a constructor for the token class it takes two parameters such as type of token and value of the token taken 
    public Token(TokenType type, String value) {
        this(type, value, 1);
    }
    //This is a constructor for the token class it takes one parameter such as type of token
    public TokenType getType() {
        return type;
    }
    //This is a constructor for the token class it takes one parameter such as value of token
    public String getValue() {
        return value;
    }
    //This is a constructor for the token class it takes one parameter such as line number where the token was found
    public int getLine() {
        return line;
    }

    public Token() {
    }
    //The override was used to provide a specific or custom string reperesentaion of the token class when its object is converted to a string or printed.
    @Override
    public String toString() {
        return "[" + type + " : \"" + value + "\" | line " + line + "]";
    }
}
