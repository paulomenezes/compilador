package br.ufrpe;

import java.util.Arrays;

public class Parser {

	private Lexer lexer;
	private Token currentToken;
	
	public Parser() {
		lexer = new Lexer();
	}

	public String parse(String content) {
		String resultado;

		try {
			lexer.reset(content);
			currentToken = lexer.nextToken(); 

			parseProgram();             
			acceptToken(TokenType.EOF); 
			
			resultado = "Ok";		    
		} catch (Exception e) {
			e.printStackTrace();
			resultado = e.getMessage();
		}
		
		return resultado;
	}

	private void acceptToken() {
		try {
			currentToken = lexer.nextToken();
			while (currentToken.getTipo() == TokenType.COMENTARIO_LINHA ||
				currentToken.getTipo() == TokenType.COMENTARIO_BLOCO) {
				currentToken = lexer.nextToken();
			}
			System.out.println(currentToken);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void acceptToken(TokenType tp) throws Exception {
		if (currentToken.getTipo() == tp) {
			currentToken = lexer.nextToken();
			while (currentToken.getTipo() == TokenType.COMENTARIO_LINHA ||
				currentToken.getTipo() == TokenType.COMENTARIO_BLOCO) {
				currentToken = lexer.nextToken();
			}
			System.out.println(currentToken);
		} else {
			throw new Exception("Token inesperado: "
					        + "foi lido um \"" + currentToken.getTipo() 
					        + "\", quando o esperado era \"" + tp + "\".");
		}
	}
	
	/**
	 *   <program> ::= <decl_global>*
	 */
	private void parseProgram() throws Exception {
		parseDeclGlobal();
	}
	
	/**
	 *   <decl_global> ::= <decl_variavel>
	 *   				|  <decl_funcao>
	 */
	private void parseDeclGlobal() throws Exception {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			parseDeclVariavel();
		} else if (Arrays.asList(tipo).contains(currentToken.getTipo()) ||
				   currentToken.getTipo() == TokenType.VOID) {
			parseDeclFuncao();
		} else {
			/* FAZ NADA */
		}
	}
	
	/**
	 *   <decl_variavel> ::= <tipo> <lista_idents> ;
	 */
	private void parseDeclVariavel() throws Exception {
		parseTipo();
		parseListaIdents();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseTipo() {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			acceptToken();
		}
	}
	
	private void parseListaIdents() throws Exception {
		acceptToken(TokenType.IDENTIFICADOR);
		while (currentToken.getTipo() == TokenType.VIRGULA) {
			acceptToken(TokenType.VIRGULA);
			acceptToken(TokenType.IDENTIFICADOR);
		}
	}
	
	private void parseDeclFuncao() throws Exception {
		parseAssinatura();
		parseBloco();
	}
	
	private void parseAssinatura() throws Exception {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo()) ||
			currentToken.getTipo() == TokenType.VOID) {
			acceptToken();
			acceptToken(TokenType.IDENTIFICADOR);
			acceptToken(TokenType.ABRE_PAR);
			parseParamFormais();
			acceptToken(TokenType.FECHA_PAR);
		}
	}
	
	private void parseParamFormais() throws Exception {
		if (currentToken.getTipo() == TokenType.FECHA_PAR) {
			
		} else {
			acceptToken(TokenType.IDENTIFICADOR);
			acceptToken(TokenType.DOIS_PONTOS);
			parseTipo();
			while (currentToken.getTipo() == TokenType.VIRGULA) {
				acceptToken(TokenType.VIRGULA);
				acceptToken(TokenType.IDENTIFICADOR);
				acceptToken(TokenType.DOIS_PONTOS);
				parseTipo();
			}
		}
	}
	
	private void parseBloco() throws Exception {
		acceptToken(TokenType.ABRE_CHAVES);
		parseListaCommandos();
		acceptToken(TokenType.FECHA_CHAVES);
	}
	
	private void parseListaCommandos() throws Exception {
		parseCommando();
		while (currentToken.getTipo() != TokenType.FECHA_CHAVES) {
			parseCommando();
		}
	}
	
	private void parseCommando() throws Exception {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			parseDeclVariavel();
		} else if (currentToken.getTipo() == TokenType.IDENTIFICADOR) {
			parseAtribuicao();
		} else if (currentToken.getTipo() == TokenType.WHILE) {
			parseIteracao();
		} else if (currentToken.getTipo() == TokenType.IF) {
			parseDecisao();
		} else if (currentToken.getTipo() == TokenType.PRNT) {
			parseEscrita();
		} else if (currentToken.getTipo() == TokenType.RETURN) {
			parseRetorno();
		} else if (currentToken.getTipo() == TokenType.ABRE_CHAVES) {
			parseBloco();
		} else if (currentToken.getTipo() == TokenType.IDENTIFICADOR) {
			parseChamadaFuncCmd();
		} else {
			/* ? */
		}
	}
	
	private void parseAtribuicao() throws Exception {
		parseListaIdents();
		acceptToken(TokenType.ATRIBUICAO);
		parseExpressao();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseIteracao() throws Exception {
		acceptToken(TokenType.WHILE);
		acceptToken(TokenType.ABRE_PAR);
		//parseExpressao();
		acceptToken(TokenType.ABRE_PAR);
		parseCommando();
	}
	
	private void parseDecisao() {
		/**/
	}
	
	private void parseEscrita() throws Exception {
		acceptToken(TokenType.PRNT);
		acceptToken(TokenType.ABRE_PAR);
		//parseListaExprs();
		acceptToken(TokenType.FECHA_PAR);
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseRetorno() throws Exception {
		acceptToken(TokenType.RETURN);
		//parseExpressao();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseChamadaFuncCmd() throws Exception {
		parseChamadaFunc();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseChamadaFunc() throws Exception {
		parseChamadaFunc();
		acceptToken(TokenType.ABRE_PAR);
		//parseListaExprs();
		acceptToken(TokenType.FECHA_PAR);
	}
	
	private void parseExpressao() throws Exception {
		parseExpressaoA();
	}
	
	private void parseExpressaoA() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.OR || 
			currentToken.getTipo() == TokenType.AND) {
			acceptToken();
			parseExpressaoB();
		} else {
			
		}
	}
	
	private void parseExpressaoB() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.COMPARACAO || 
			currentToken.getTipo() == TokenType.DIFERENCA || 
			currentToken.getTipo() == TokenType.MENOR_QUE || 
			currentToken.getTipo() == TokenType.MAIOR_QUE || 
			currentToken.getTipo() == TokenType.MENOR_IGUAL || 
			currentToken.getTipo() == TokenType.MAIOR_IGUAL) {
			acceptToken();
			parseExpressaoC();
		} else {
			
		}
	}
	
	private void parseExpressaoC() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.OP_MAIS || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			parseExpressaoD();
		} else {
			
		}
	}
	
	private void parseExpressaoD() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.OP_MULTIPLICACAO || 
			currentToken.getTipo() == TokenType.OP_DIVISAO || 
			currentToken.getTipo() == TokenType.OP_MODULO) {
			acceptToken();
			parseExpressaoE();
		} else {
			
		}
	}
	
	private void parseExpressaoE() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.NOT || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			parseExpressaoBasica();
		} else {
			
		}
	}
	
	private void parseExpressaoBasica() throws Exception {
		if (currentToken.getTipo() == TokenType.ABRE_PAR) {
			acceptToken();
			parseExpressao();
			acceptToken(TokenType.FECHA_PAR);
		} else if (currentToken.getTipo() == TokenType.NOT) {
			acceptToken();
			parseExpressaoBasica();
		} else if (currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			parseExpressaoBasica();
		} else if (currentToken.getTipo() == TokenType.NUMERO_LITERAL || 
				   currentToken.getTipo() == TokenType.CARACTER_LITERAL || 
				   currentToken.getTipo() == TokenType.NUMERO_FLUTUANTE || 
				   currentToken.getTipo() == TokenType.CARACTER_LITERAL || 
				   currentToken.getTipo() == TokenType.IDENTIFICADOR) {
			acceptToken();
		} 
	}
	
	/**
	 *   <expr> ::= <exprBasic> <restExpr>
	 */
	private void parseExpr() throws Exception {
		parseExprBasic();
		parseRestExpr();
	}

	/**
	 *   <restExpr> ::= "+" <expr>
	 *                | "*" <expr>
	 *                | --vazio--
	 */
	private void parseRestExpr() throws Exception {
		
		if (currentToken.getTipo() == TokenType.OP_MAIS) {
			acceptToken();
			parseExpr();
		} else 	if (currentToken.getTipo() == TokenType.OP_MULTIPLICACAO) {
			acceptToken();
			parseExpr();
		} else {
			/* faz nada */
			
		}		
	}
	
	/**
	 *   <exprBasic> ::= "(" <expr> ")" 
	 *                 | NUM_LITERAL
	 */
	private void parseExprBasic() throws Exception {
		if (currentToken.getTipo() == TokenType.ABRE_PAR) {
			acceptToken();
			parseExpr();
			acceptToken(TokenType.FECHA_PAR);
			
		} else if (currentToken.getTipo() == TokenType.NUMERO_LITERAL) {
			acceptToken();
			
		} else {
			throw new Exception("Token inesperado: " + currentToken.getTipo() + ".");
		}
	}
}