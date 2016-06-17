package br.ufrpe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import br.ufrpe.tree.*;
import br.ufrpe.tree.comando.*;
import br.ufrpe.tree.expressao.*;

public class Parser {

	private Lexer lexer;
	private Token currentToken;
	
	public Parser() {
		lexer = new Lexer();
	}

	public Programa parse(String content) {
		Programa resultado = null;

		try {
			lexer.reset(content);
			currentToken = lexer.nextToken(); 

			resultado = parseProgram();             
			acceptToken(TokenType.EOF); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	private void acceptToken() {
		try {
			System.out.println(currentToken);
			currentToken = lexer.nextToken();
			while (currentToken.getTipo() == TokenType.COMENTARIO_LINHA ||
				currentToken.getTipo() == TokenType.COMENTARIO_BLOCO) {
				currentToken = lexer.nextToken();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void acceptToken(TokenType tokenType) throws Exception {
		if (currentToken.getTipo() == tokenType) {
			System.out.println(currentToken);
			currentToken = lexer.nextToken();
			while (currentToken.getTipo() == TokenType.COMENTARIO_LINHA ||
				currentToken.getTipo() == TokenType.COMENTARIO_BLOCO) {
				currentToken = lexer.nextToken();
			}
		} else {
			throw new Exception("Token inesperado: "
					        + "foi lido um \"" + currentToken.getTipo() 
					        + "\", quando o esperado era \"" + tokenType + "\".");
		}
	}
	
	private Programa parseProgram() throws Exception {
		Programa programa = new Programa();

		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL, TokenType.VOID };
		while (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			programa.addLast(parseDeclGlobal());
		}
		
		return programa;
	}
	
	private DeclGlobal parseDeclGlobal() throws Exception {
		if (currentToken.getTipo() == TokenType.VOID){
			acceptToken(TokenType.VOID);
			String id = currentToken.getLexema();
			acceptToken(TokenType.IDENTIFICADOR);
			return parseDeclFuncao(Tipo.VOID, id);
		} else {
			Tipo tipo = parseTipo();
			String id = currentToken.getLexema();
			acceptToken(TokenType.IDENTIFICADOR);
			if (currentToken.getTipo() == TokenType.ABRE_PAR) {
				return parseDeclFuncao(tipo, id);
			} else{
				return parseDeclVariavel(tipo, id);
			}
		}
	}
	
	private DeclVariavel parseDeclVariavel(Tipo tipo, String id) throws Exception {
		LinkedList<String> ids = parseListaIdents();
		ids.add(id);
		
		acceptToken(TokenType.PONTO_VIRGULA);
		
		return new DeclVariavel(ids, tipo);
	}
	
	private Tipo parseTipo() {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			Tipo t = null;
			
			switch (currentToken.getTipo()) {
				case INT:
					t = Tipo.INT;
					break;
				case FLOAT:
					t = Tipo.FLOAT;
					break;
				case CARACTER_LITERAL:
					t = Tipo.STRING;
					break;
				default:
					break;
			}
			
			acceptToken();
			
			return t;
		}
		
		return null;
	}
	
	private LinkedList<String> parseListaIdents() throws Exception {
		LinkedList<String> ids = new LinkedList<>();
		
		while (currentToken.getTipo() == TokenType.VIRGULA) {
			acceptToken(TokenType.VIRGULA);
			
			ids.add(currentToken.getLexema());
			acceptToken(TokenType.IDENTIFICADOR);
		}
		
		return ids;
	}
	
	private DeclFuncao parseDeclFuncao(Tipo tipo, String id) throws Exception {
		Assinatura assinatura = parseAssinatura(tipo, id);
		Bloco bloco = parseBloco();
		
		return new DeclFuncao(assinatura, bloco);
	}
	
	private Assinatura parseAssinatura(Tipo tipo, String id) throws Exception {
		acceptToken(TokenType.ABRE_PAR);
		LinkedList<DeclVariavel> paramFormais = parseParamFormais();
		acceptToken(TokenType.FECHA_PAR);
		
		return new Assinatura(id, paramFormais, tipo);
	}
	
	private LinkedList<DeclVariavel> parseParamFormais() throws Exception {
		LinkedList<DeclVariavel> paramFormais = new LinkedList<>();
		
		if (currentToken.getTipo() != TokenType.FECHA_PAR) {
			String id = currentToken.getLexema();
			acceptToken(TokenType.IDENTIFICADOR);
			acceptToken(TokenType.DOIS_PONTOS);
			Tipo tipo = parseTipo();
			paramFormais.add(new DeclVariavel(id, tipo));
			
			while (currentToken.getTipo() == TokenType.VIRGULA) {
				acceptToken(TokenType.VIRGULA);
				String id1 = currentToken.getLexema();
				acceptToken(TokenType.IDENTIFICADOR);
				acceptToken(TokenType.DOIS_PONTOS);
				Tipo tipo1 = parseTipo();
				
				paramFormais.add(new DeclVariavel(id1, tipo1));
			}
		}
		
		return paramFormais;
	}
	
	private Bloco parseBloco() throws Exception {
		acceptToken(TokenType.ABRE_CHAVES);
		ArrayList<Comando> listaCommandos = parseListaCommandos();
		acceptToken(TokenType.FECHA_CHAVES);
		
		return new Bloco(listaCommandos);
	}
	
	private ArrayList<Comando> parseListaCommandos() throws Exception {
		ArrayList<Comando> listaComandos = new ArrayList<>();
		listaComandos.add(parseCommando());
		
		while (currentToken.getTipo() != TokenType.FECHA_CHAVES) {
			listaComandos.add(parseCommando());
		}
		
		return listaComandos;
	}
	
	private Comando parseCommando() throws Exception {
		TokenType[] tipo = { TokenType.INT, TokenType.CHAR, TokenType.FLOAT, TokenType.CARACTER_LITERAL };
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			Tipo t = parseTipo();
			String id = currentToken.getLexema();
			acceptToken(TokenType.IDENTIFICADOR);
			return parseDeclVariavel(t, id);
		} else if (currentToken.getTipo() == TokenType.IDENTIFICADOR) {
			String id = currentToken.getLexema();
			acceptToken();
			if(currentToken.getTipo() == TokenType.ABRE_PAR){
				return parseChamadaFuncCmd(id);
			} else {
				return parseAtribuicao(id);
			}
		} else if (currentToken.getTipo() == TokenType.WHILE) {
			return parseIteracao();
		} else if (currentToken.getTipo() == TokenType.IF) {
			return parseDecisao();
		} else if (currentToken.getTipo() == TokenType.PRNT) {
			return parseEscrita();
		} else if (currentToken.getTipo() == TokenType.RETURN) {
			return parseRetorno();
		} else if (currentToken.getTipo() == TokenType.ABRE_CHAVES) {
			return parseBloco();
		} else {
			/* FAZ NADA */
			return null;
		}
	}
	
	private Atribuicao parseAtribuicao(String id) throws Exception {
		//LinkedList<String> list = parseListaIdents();
		acceptToken(TokenType.ATRIBUICAO);
		Expressao expressao = parseExpressao();
		acceptToken(TokenType.PONTO_VIRGULA);
		
		return new Atribuicao(id, expressao);
	}
	
	private Iteracao parseIteracao() throws Exception {
		acceptToken(TokenType.WHILE);
		acceptToken(TokenType.ABRE_PAR);
		Expressao expressao = parseExpressao();
		acceptToken(TokenType.FECHA_PAR);
		Comando comando = parseCommando();
		
		return new Iteracao(expressao, comando);
	}
	
	private Decisao parseDecisao() throws Exception {
		Decisao d = parseRestoDecisao();
		if (currentToken.getTipo() == TokenType.ELSE) {
			acceptToken(TokenType.ELSE);
			Comando cmd = parseCommando();
			
			d.setCmdElse(cmd);
		}
		
		return d;
	}
	
	private Decisao parseRestoDecisao() throws Exception {
		acceptToken(TokenType.IF);
		Expressao expressao = parseExpressao();
		acceptToken(TokenType.THEN);
		Comando comando = parseCommando();
		
		return new Decisao(expressao, comando);
	}
	
	private Escrita parseEscrita() throws Exception {
		acceptToken(TokenType.PRNT);
		acceptToken(TokenType.ABRE_PAR);
		Escrita escrita = new Escrita(parseListaExprs());
		acceptToken(TokenType.FECHA_PAR);
		acceptToken(TokenType.PONTO_VIRGULA);
		
		return escrita;
	}
	
	private Retorno parseRetorno() throws Exception {
		acceptToken(TokenType.RETURN);
		Expressao expresao = parseExpressao();
		acceptToken(TokenType.PONTO_VIRGULA);
		
		return new Retorno(expresao);
	}
	
	private ChamadaFunc parseChamadaFuncCmd(String id) throws Exception {
		LinkedList<Expressao> listaExprs = parseChamadaFunc();
		acceptToken(TokenType.PONTO_VIRGULA);
		
		return new ChamadaFunc(id, listaExprs);
	}
	
	private LinkedList<Expressao> parseChamadaFunc() throws Exception {
		acceptToken(TokenType.ABRE_PAR);
		LinkedList<Expressao> listaExprs = parseListaExprs();
		acceptToken(TokenType.FECHA_PAR);
		
		return listaExprs;
	}
	
	private LinkedList<Expressao> parseListaExprs() throws Exception {
		LinkedList<Expressao> listaExprs = new LinkedList<>();
		
		TokenType[] tipo = { TokenType.ABRE_PAR, TokenType.NOT, TokenType.OP_MENOS, TokenType.NUMERO_LITERAL, TokenType.CARACTER_LITERAL, TokenType.NUMERO_FLUTUANTE, TokenType.IDENTIFICADOR};
		if (Arrays.asList(tipo).contains(currentToken.getTipo())) {
			listaExprs.add(parseExpressao());
			while (currentToken.getTipo() == TokenType.VIRGULA) {
				acceptToken();
				listaExprs.add(parseExpressao());
			}
		}
		
		return listaExprs;
	}
	
	private Expressao parseExpressao() throws Exception {
		return parseExpressaoA();
	}
	
	private Expressao parseExpressaoA() throws Exception {
		Expressao expr1 = parseExpressaoB();
		Expressao expr2 = null;
		String tipo = "";
		
		if (currentToken.getTipo() == TokenType.OR || 
			currentToken.getTipo() == TokenType.AND) {
			tipo = currentToken.getLexema();
			
			acceptToken();
			expr2 = parseExpressaoA();
		} 
		
		return new ExprLogica(expr1, expr2, tipo);
	}
	
	private Expressao parseExpressaoB() throws Exception {
		Expressao expr1 = parseExpressaoC();
		Expressao expr2 = null;
		String tipo = "";
		
		if (currentToken.getTipo() == TokenType.COMPARACAO || 
			currentToken.getTipo() == TokenType.DIFERENCA || 
			currentToken.getTipo() == TokenType.MENOR_QUE || 
			currentToken.getTipo() == TokenType.MAIOR_QUE || 
			currentToken.getTipo() == TokenType.MENOR_IGUAL || 
			currentToken.getTipo() == TokenType.MAIOR_IGUAL) {
			tipo = currentToken.getLexema();
			
			acceptToken();
			expr2 = parseExpressaoB();
		}  
		
		return new ExprLogica(expr1, expr2, tipo);
	}
	
	private Expressao parseExpressaoC() throws Exception {
		Expressao expr1 = parseExpressaoD();
		Expressao expr2 = null;
		String tipo = "";
		
		if (currentToken.getTipo() == TokenType.OP_MAIS || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			tipo = currentToken.getLexema();
			
			acceptToken();
			expr2 = parseExpressaoC();
		}
		
		return new ExprAritmetica(expr1, expr2, tipo);
	}
	
	
	private Expressao parseExpressaoD() throws Exception {
		Expressao expr1 = parseExpressaoE();
		Expressao expr2 = null;
		String tipo = "";
		
		if (currentToken.getTipo() == TokenType.OP_MULTIPLICACAO || 
			currentToken.getTipo() == TokenType.OP_DIVISAO || 
			currentToken.getTipo() == TokenType.OP_MODULO) {
			tipo = currentToken.getLexema();
			
			acceptToken();
			expr2 = parseExpressaoD();
		} 
		
		return new ExprAritmetica(expr1, expr2, tipo);
	}
	
	private Expressao parseExpressaoE() throws Exception {
		Expressao expr1 = parseExpressaoBasica();
		Expressao expr2 = null;
		String tipo = "";
		
		if (currentToken.getTipo() == TokenType.NOT || 
			currentToken.getTipo() == TokenType.OP_MENOS) {
			tipo = currentToken.getLexema();
			
			acceptToken();
			expr2 = parseExpressaoE();
		}
		
		return new ExprAritmetica(expr1, expr2, tipo);
	}
	
	private Expressao parseExpressaoBasica() throws Exception {
		if (currentToken.getTipo() == TokenType.ABRE_PAR) {
			acceptToken();
			Expressao expressao = parseExpressao();
			acceptToken(TokenType.FECHA_PAR);
			
			return expressao;
		} else if (currentToken.getTipo() == TokenType.NOT) {
			acceptToken();
			return new ExpUnaria("not", parseExpressaoBasica());
		} else if (currentToken.getTipo() == TokenType.OP_MENOS) {
			acceptToken();
			return new ExpUnaria("-", parseExpressaoBasica());
		} else if (currentToken.getTipo() == TokenType.IDENTIFICADOR) {

			String tipo = currentToken.getLexema();
			acceptToken();

			if (currentToken.getTipo() == TokenType.ABRE_PAR)
				return new ChamadaFunc(tipo, parseChamadaFunc());
			
			return new StringLiteral(tipo);
		} else if (currentToken.getTipo() == TokenType.NUMERO_LITERAL) {
			int literal = Integer.parseInt(currentToken.getLexema());
			acceptToken();
			
			return new IntLiteral(literal);
		} else if (currentToken.getTipo() == TokenType.CARACTER_LITERAL) {
			String literal = currentToken.getLexema();
			acceptToken();
			
			return new StringLiteral(literal);
		}
		
		return null;
	}
}