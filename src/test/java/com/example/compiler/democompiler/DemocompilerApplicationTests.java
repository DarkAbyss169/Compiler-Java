package com.example.compiler.democompiler;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DemocompilerApplicationTests {

	@Test
	void testScannerTokenize() {
		String code = "int x = 10;\nif (x >= 5) {\n    print(\"hello\");\n}";
		Scanner scanner = new Scanner(code);
		List<Token> tokens = scanner.tokenize();

		assertNotNull(tokens);
		assertTrue(tokens.size() > 0);
		assertEquals(Token.TokenType.KEYWORD, tokens.get(0).getType());
		assertEquals("int", tokens.get(0).getValue());
	}

}
