package parser;

public class UnaryMinus extends Expression {
	
	public Expression exp;
	
	public UnaryMinus(Expression exp){
		super();
		this.exp = exp;
	}

	@Override
	public String toString() {
		return "UnaryMinus("+exp+")";
	}
	
}
