package br.ufrpe;

import java.io.IOException;
import java.io.InputStream;

public class Lexer {
	private InputStream input;
	private int nextChar;
	
	public Lexer() {
		this.nextChar = -1;
	} 
	
	public void reset(InputStream in) throws Exception {
		try {
			this.input = in;
			this.nextChar = in.read();		
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private int readByte() throws Exception {
		int theByte;
		
		try {
			theByte = input.read();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
		
		return theByte;
	}
	
	public Token nextToken() throws Exception {
		TokenType tipo;
		
		if (this.nextChar == -1) {
			return new Token(TokenType.EOF);
		}

		if (nextChar >= '0' && nextChar <= '9') {
			tipo = TokenType.NUMERO_LITERAL;
		} else if (nextChar == ';') {
			tipo = TokenType.PONTO_VIRGULA;
		} else if (nextChar == '+') {
			tipo = TokenType.OP_MAIS;
		} else if (nextChar == '*') {
			tipo = TokenType.OP_MULTIPLICACAO;
		} else if (nextChar == '(') {
			tipo = TokenType.ABRE_PAR;
		} else if (nextChar == ')') {
			tipo = TokenType.FECHA_PAR;
		} else {
			throw new Exception("Caracter inesperado: " + (char)nextChar);
		}
		
		String lexema = "" + (char)nextChar;
		
		this.nextChar = this.readByte();

		return new Token(tipo, lexema);
	}
}
