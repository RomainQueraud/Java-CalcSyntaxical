package parser;

import lexer.OP;

public class BinaryExpression extends Expression {
	
	public OP op;
	public Expression exp1;
	public Expression exp2;
	
	public BinaryExpression(OP op, Expression exp1, Expression exp2){
		this.op = op;
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public String toString() {
		return "BinaryExpression("+op+", "+exp1+", "+exp2+")";
	}

}
