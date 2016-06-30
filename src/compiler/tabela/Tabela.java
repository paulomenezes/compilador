package compiler.tabela;

import java.util.LinkedList;

import compiler.tree.Tipo;

public class Tabela {
	private LinkedList<Declaracao> declg;
	private LinkedList<Funcao> decls;
	private static Tabela tabela;
	
	private Tabela() {
		decls = new LinkedList<>();
		declg = new LinkedList<>();
	}
	
	public static synchronized Tabela getInstance() {
		if (tabela == null)
			tabela = new Tabela();

		return tabela;
	}
	
	public boolean addFunc(Tipo tipo, String nome) {
		Funcao dec = new Funcao(tipo, nome);
		if(!decls.contains(dec) && !declg.contains(new Declaracao(tipo, nome,null,0))) {
			decls.addFirst(dec);
			return true;
		}
		else return false;
	}
	
	public boolean addVar(Tipo tipo, String nome, Object valor) {
		if(decls.size()==0 || decls.getFirst().getEscopoAtual()==0){
			Declaracao dec = new Declaracao(tipo, nome,valor,0);
			if(!decls.contains(new Funcao (tipo, nome)) && !declg.contains(dec)) {
				declg.addFirst(dec);
				return true;
			}
			else return false;
		}
		else{
			Funcao f = decls.getFirst();
			Declaracao dec = new Declaracao(tipo,nome,valor,f.getEscopoAtual());
			if(!decls.contains(new Funcao(tipo,nome)) && !f.getList().contains(dec)) {
				f.addList(dec);
				decls.set(0, f);
				return true;
			}
			else return false;
		}
		
	}
	
	public void addEscopo(){
		Funcao f = decls.getFirst();
		f.addEscopoAtual();
		decls.set(0, f);
	}
	
	public void removeEscopo(){
		Funcao f = decls.getFirst();
		f.removeEscopoAtual();
		decls.set(0, f);	
	}
	
	public boolean atribuir(String nome, Object value){
		Funcao f = decls.getFirst();
		boolean aux = f.atribuir(nome, value);
		
		if(!aux){
			for(int i = 0; i < declg.size(); i++){
				Declaracao d =declg.get(i);
				if(d.getNome().equals(nome)) {
					d.setValor(value);
					declg.set(i, d);
					return true;
				}
			}
		}
		return aux;
	}
	
	public Tipo getTipo(String nome){
		Funcao f = decls.getFirst();
		Tipo t = f.getTipo(nome);
		if(t==null){
			for(int i = 0; i < declg.size(); i++){
				Declaracao d =declg.get(i);
				if(d.getNome().equals(nome)) return d.getTipo();
			}
		}
		return t;
	}
}
