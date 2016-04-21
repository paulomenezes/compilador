package br.ufrpe;

public class Lexer {
	private char[] input;
	private int nextChar;
	private int nextIndex;
	
	public Lexer() {
		this.nextChar = -1;
	} 
	
	public void reset(String content) {
		input = content.toCharArray();
		nextIndex = 0;
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
		} else if ((nextChar >= 97 && nextChar <= 122) ||
				   (nextChar >= 65 && nextChar <= 90)) {
			do {
				lexema += "" + (char)nextChar;
				next();
				
				bigValue = true;
			} while((nextChar >= 97 && nextChar <= 122) ||
					(nextChar >= 65 && nextChar <= 90));
			
			if (lexema.equals("if")) { tipo = TokenType.IF;
			} else if (lexema.equals("else")) { tipo = TokenType.ELSE;
			} else if (lexema.equals("and")) { tipo = TokenType.AND;
			} else if (lexema.equals("or")) { tipo = TokenType.OR;
			} else if (lexema.equals("not")) { tipo = TokenType.NOT;
			} else if (lexema.equals("then")) { tipo = TokenType.THEN;
			} else if (lexema.equals("while")) { tipo = TokenType.WHILE;
			} else if (lexema.equals("return")) { tipo = TokenType.RETURN;
			} else if (lexema.equals("float")) { tipo = TokenType.FLOAT;
			} else if (lexema.equals("char")) { tipo = TokenType.CHAR;
			} else if (lexema.equals("void")) { tipo = TokenType.VOID;
			} else if (lexema.equals("prnt")) { tipo = TokenType.PRNT;
			} else if (lexema.equals("int")) { tipo = TokenType.INT;
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
				do {
					next();
					if (this.nextChar != '\n' && this.nextChar != '\r')
						lexema += "" + (char)nextChar;
				} while(this.nextChar != '\n' && this.nextChar != '\r');
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
