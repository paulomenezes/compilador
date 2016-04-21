package br.ufrpe;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) throws Exception {
		Token token = null;
		
		Lexer lexer = new Lexer();
		
		byte[] bytes = Files.readAllBytes(Paths.get("main.yeled"));
		lexer.reset(new String(bytes, StandardCharsets.UTF_8));
		
		do {
			token = lexer.nextToken();
			System.out.println(token);
		
		} while (token.getTipo() != TokenType.EOF);
	}
}
