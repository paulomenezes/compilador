package compiler.exceptions;

public class SemanticsException extends Exception{
	private String erro;

	public SemanticsException(String erro) {
		super(erro);
		this.erro = erro;
	}
	
}
