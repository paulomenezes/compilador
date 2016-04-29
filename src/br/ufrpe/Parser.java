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
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL, TokenType.VOID };
		while (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			parseDeclGlobal();
		}
	}
	
	/**
	 *   <decl_global> ::= <decl_variavel>
	 *   				|  <decl_funcao>
	 */
	private void parseDeclGlobal() throws Exception {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if(currentToken.getTipo() == TokenType.VOID){
			acceptToken(TokenType.VOID);
			acceptToken(TokenType.IDENTIFICADOR);
			parseDeclFuncao();
		}
		else{
			parseTipo();
			acceptToken(TokenType.IDENTIFICADOR);
			if(currentToken.getTipo()==TokenType.ABRE_PAR){
				parseDeclFuncao();
			}
			else{
				parseDeclVariavel();
			}
		}
		/*if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			parseDeclVariavel();
		} else if (Arrays.asList(tipo).contains(currentToken.getTipo()) ||
				   currentToken.getTipo() == TokenType.VOID) {
			parseDeclFuncao();
		} else {
			/* FAZ NADA 
		}*/
	}
	
	/**
	 *   <decl_variavel> ::= <tipo> <lista_idents> ;
	 */
	private void parseDeclVariavel() throws Exception {
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
		
		while (currentToken.getTipo() == TokenType.VIRGULA) {
			acceptToken(TokenType.VIRGULA);
			acceptToken(TokenType.IDENTIFICADOR);
			System.out.println("teste");
		}
	}
	
	private void parseDeclFuncao() throws Exception {
		parseAssinatura();
		parseBloco();
	}
	
	private void parseAssinatura() throws Exception {
		acceptToken(TokenType.ABRE_PAR);
		parseParamFormais();
		acceptToken(TokenType.FECHA_PAR);
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
			parseTipo();
			acceptToken(TokenType.IDENTIFICADOR);
			parseDeclVariavel();
		} else if (currentToken.getTipo() == TokenType.IDENTIFICADOR) {
			acceptToken();
			if(currentToken.getTipo() == TokenType.ABRE_PAR){
				parseChamadaFuncCmd();
			} else {
				parseAtribuicao();
			}
			
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
		parseExpressao();
		acceptToken(TokenType.FECHA_PAR);
		parseCommando();
	}
	
	private void parseDecisao() throws Exception {
		/**/
		parseRestoDecisao();
		if(currentToken.getTipo()==TokenType.ELSE){
			acceptToken(TokenType.ELSE);
			parseCommando();
		}
	}
	
	private void parseRestoDecisao() throws Exception {
		/*Fatoração à Esquerda*/
		acceptToken(TokenType.IF);
		parseExpressao();
		acceptToken(TokenType.THEN);
		parseCommando();
	}
	
	private void parseEscrita() throws Exception {
		acceptToken(TokenType.PRNT);
		acceptToken(TokenType.ABRE_PAR);
		parseListaExprs();
		acceptToken(TokenType.FECHA_PAR);
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseRetorno() throws Exception {
		acceptToken(TokenType.RETURN);
		parseExpressao();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseChamadaFuncCmd() throws Exception {
		parseChamadaFunc();
		acceptToken(TokenType.PONTO_VIRGULA);
	}
	
	private void parseChamadaFunc() throws Exception {
		acceptToken(TokenType.ABRE_PAR);
		parseListaExprs();
		acceptToken(TokenType.FECHA_PAR);
	}
	
	private void parseListaExprs() throws Exception {
		TokenType[] tipo = { TokenType.ABRE_PAR, TokenType.NOT, TokenType.OP_MENOS, TokenType.NUMERO_LITERAL, TokenType.CARACTER_LITERAL, TokenType.NUMERO_FLUTUANTE, TokenType.IDENTIFICADOR};
		if(Arrays.asList(tipo).contains(currentToken.getTipo())){
			parseExpressao();
			while(currentToken.getTipo() == TokenType.VIRGULA){
				acceptToken();
				parseExpressao();
			}
		}
	}
	
	private void parseExpressao() throws Exception {
		parseExpressaoA();
	}
	
	private void parseExpressaoA() throws Exception {
		parseExpressaoB();
		if (currentToken.getTipo() == TokenType.OR || 
			currentToken.getTipo() == TokenType.AND) {
			acceptToken();
			parseExpressaoA();
		} 
	}
	
	private void parseExpressaoB() throws Exception {
		parseExpressaoC();
		if (currentToken.getTipo() == TokenType.COMPARACAO || 
			currentToken.getTipo() == TokenType.DIFERENCA || 
			currentToken.getTipo() == TokenType.MENOR_QUE || 
			currentToken.getTipo() == TokenType.MAIOR_QUE || 
			currentToken.getTipo() == TokenType.MENOR_IGUAL || 
			currentToken.getTipo() == TokenType.MAIOR_IGUAL) {
			acceptToken();
			parseExpressaoB();
		} 
	}
	
	private void parseExpressaoC() throws Exception {
		parseExpressaoD();
		if (currentToken.getTipo() == TokenType.OP_MAIS || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			parseExpressaoC();
		}
	}
	
	
	private void parseExpressaoD() throws Exception {
		parseExpressaoE();
		if (currentToken.getTipo() == TokenType.OP_MULTIPLICACAO || 
			currentToken.getTipo() == TokenType.OP_DIVISAO || 
			currentToken.getTipo() == TokenType.OP_MODULO) {
			acceptToken();
			parseExpressaoD();
		} 
	}
	
	private void parseExpressaoE() throws Exception {
		parseExpressaoBasica();
		if (currentToken.getTipo() == TokenType.NOT || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			parseExpressaoE();
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
			if (currentToken.getTipo() == TokenType.ABRE_PAR)
				parseChamadaFunc();
		}
	}
}