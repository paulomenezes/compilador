package br.ufrpe;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.ufrpe.tree.Programa;

public class Main {
	public static void main(String[] args) throws Exception {
		Token token = null;
		
		Lexer lexer = new Lexer();
		
		byte[] bytes = Files.readAllBytes(Paths.get("main.yeled"));
		String content = new String(bytes, StandardCharsets.UTF_8);
		lexer.reset(content);
		
		do {
			token = lexer.nextToken();
		} while (token.getTipo() != TokenType.EOF);
		
		Parser parser = new Parser();
		Programa result = parser.parse(content);
	}
}
