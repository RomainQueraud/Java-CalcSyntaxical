package parser;

public abstract class AST {
	@Override
	public abstract String toString();
	
	public abstract int eval();
}
