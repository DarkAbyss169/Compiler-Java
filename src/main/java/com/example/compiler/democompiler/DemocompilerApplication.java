package com.example.compiler.democompiler;

import java.util.List;

// Main entry point for our compiler scanner
public class DemocompilerApplication {

    public static void main(String[] args) {
        // Sample code used to test our scanner
        String testCode = 
            "// Sample compiler test\n" +
            "int x = 10;\n" +
            "float y = 2.5;\n" +
            "if (x >= 5) {\n" +
            "    print(\"x is greater\");\n" +
            "} else {\n" +
            "    x = x + 1;\n" +
            "}";

        System.out.println("Source Program:");
        System.out.println(testCode);
        System.out.println("\nTokens:");

        Scanner scanner = new Scanner(testCode);
        List<Token> tokens = scanner.tokenize();

        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
