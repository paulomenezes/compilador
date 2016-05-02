package br.ufrpe;

import java.util.Hashtable;

public class Lexer {
	private char[] input;
	private int nextChar;
	private int nextIndex;
	
	private Hashtable<String, TokenType> keywords = new Hashtable<String, TokenType>();
		
	public Lexer() {
		this.nextChar = -1;
		
		keywords.put("if", TokenType.IF);
		keywords.put("else", TokenType.ELSE);
		keywords.put("and", TokenType.AND);
		keywords.put("or", TokenType.OR);
		keywords.put("not", TokenType.NOT);
		keywords.put("then", TokenType.THEN);
		keywords.put("while", TokenType.WHILE);
		keywords.put("return", TokenType.RETURN);
		keywords.put("float", TokenType.FLOAT);
		keywords.put("char", TokenType.CHAR);
		keywords.put("void", TokenType.VOID);
		keywords.put("prnt", TokenType.PRNT);
		keywords.put("int", TokenType.INT);		
	} 
	
	public void reset(String content) {
		input = content.toCharArray();
		nextIndex = 0;
		if (input.length > 0)
			nextChar = input[nextIndex];
	}
	
	private void next() {
		if (nextIndex + 1 < input.length) {
			nextIndex++;
			nextChar = input[nextIndex];
		} else {
			nextIndex++;
		}
	}
	
	public Token nextToken() throws Exception {
		TokenType tipo;
		
		while (this.nextChar == ' ' || this.nextChar == '\t'
				|| this.nextChar == '\n' || this.nextChar == '\r') {
			next();
		}
		
		if (nextIndex + 1 > input.length) {
			return new Token(TokenType.EOF);
		}
		
		boolean bigValue = false;
		String lexema = "";

		if (nextChar >= '0' && nextChar <= '9') {
			do {
				lexema += "" + (char)nextChar;
				next();
				
				bigValue = true;
			} while(nextChar >= '0' && nextChar <= '9');
			tipo = TokenType.NUMERO_LITERAL;
		} else if ((nextChar >= 'a' && nextChar <= 'z') ||
				   (nextChar >= 'A' && nextChar <= 'Z')) {
			do {
				lexema += "" + (char)nextChar;
				next();
				
				bigValue = true;
			} while((nextChar >= 'a' && nextChar <= 'z') ||
					(nextChar >= 'A' && nextChar <= 'Z'));
			
			if (keywords.containsKey(lexema)) {
				tipo = (TokenType) keywords.get(lexema);
			} else {
				tipo = TokenType.IDENTIFICADOR; 
			}
		} else if (nextChar == '=') { 
			lexema = "=";
			next();
			if (nextChar == '=') { 
				lexema = "==";
				tipo = TokenType.COMPARACAO;
				next();
			} else {
				tipo = TokenType.ATRIBUICAO;
			}
			
			bigValue = true;
		} else if (nextChar == '<') { tipo = TokenType.MENOR_QUE;
		} else if (nextChar == '>') { 
			lexema = ">";
			next();
			if (nextChar == '>') { 
				lexema = ">>";
				tipo = TokenType.COMENTARIO_BLOCO;
				
				int fechaComentario = 0;
				do {
					next();
					lexema += "" + (char)nextChar;
					if (this.nextChar == '<')
						fechaComentario++;
				} while(fechaComentario < 2);
				next();
			} else {
				tipo = TokenType.MAIOR_QUE;
			}
			
			bigValue = true;
		} else if (nextChar == '!') { tipo = TokenType.DIFERENCA;
		} else if (nextChar == '+') { tipo = TokenType.OP_MAIS;
		} else if (nextChar == '-') { tipo = TokenType.OP_MENOS;
		} else if (nextChar == '*') { 
			lexema = "*";
			next();
			if (nextChar == '*') { 
				lexema = "**";
				tipo = TokenType.COMENTARIO_LINHA;
				do {
					next();
					if (this.nextChar != '\n' && this.nextChar != '\r')
						lexema += "" + (char)nextChar;
				} while(this.nextChar != '\n' && this.nextChar != '\r');
			} else {
				tipo = TokenType.OP_MULTIPLICACAO;
			}
			
			bigValue = true;
		} else if (nextChar == '/') { tipo = TokenType.OP_DIVISAO;
		} else if (nextChar == '%') { tipo = TokenType.OP_MODULO;
		} else if (nextChar == '(') { tipo = TokenType.ABRE_PAR;
		} else if (nextChar == ')') { tipo = TokenType.FECHA_PAR;
		} else if (nextChar == ',') { tipo = TokenType.VIRGULA;
		} else if (nextChar == ';') { tipo = TokenType.PONTO_VIRGULA;
		} else if (nextChar == ':') { tipo = TokenType.DOIS_PONTOS;
		} else if (nextChar == '{') { tipo = TokenType.ABRE_CHAVES;
		} else if (nextChar == '}') { tipo = TokenType.FECHA_CHAVES;
		} else if (nextChar == '\'') { 
			tipo = TokenType.CARACTER_LITERAL;

			lexema = "'";
			do {
				next();
				lexema += "" + (char)nextChar;
			} while(this.nextChar != '\'');
			next();
			bigValue = true;
		} else {
			throw new Exception("Caracter inesperado: " + (char)nextChar);
		}
		
		if (!bigValue) {
			lexema = "" + (char)nextChar;
			this.next();
		}
		
		return new Token(tipo, lexema);
	}
}
