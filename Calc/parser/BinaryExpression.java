package parser;

import lexer.OP;
import lexer.EOP;

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

	@Override
	public int eval() {
		int v1 = this.exp1.eval();
		int v2 = this.exp2.eval();
		switch(this.op.toString()){
			case "+": return v1+v2;
			case "-": return v1-v2;
			case "*": return v1*v2;
			case "/": return v1/v2;
			case "==": return (v1==v2) ? 1 : 0;
			default: throw new RuntimeException("Erreur : Opérateur non reconnu");
		}
	}
}
