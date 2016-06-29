package compiler.exceptions;

public class YeledCompilerException extends Exception{
	private int line;
	private int col;
	
	public YeledCompilerException(String result, int line, int col) {
		super(result);
		this.line = line;
		this.col = col;
	}
	
	public int getCol() {
		return col;
	}

	public int getLine() {
		return line;
	}
}
