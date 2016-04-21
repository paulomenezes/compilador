package br.ufrpe;

public class Token {
	private TokenType tipo;
	private String lexema;
	
	public Token(TokenType tokenType) {
		this.tipo = tokenType;
	}

	public Token(TokenType tokenType, String lexema) {
		this.tipo = tokenType;
		this.lexema = lexema;
	}

	public TokenType getTipo() {
		return tipo;
	}
	
	public void setTipo(TokenType tipo) {
		this.tipo = tipo;
	}
	
	public String getLexema() {
		return lexema;
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	
	@Override
	public String toString() {
		if (lexema != null) {
			return "[" + tipo + ", " + lexema + "]";
		} else {
			return "[" + tipo + "]";
		}
	}
}
