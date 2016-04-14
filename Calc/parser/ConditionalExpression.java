package parser;

public class ConditionalExpression extends Expression {

	public Expression exp1;
	public Expression exp2;
	public Expression exp3;
	
	public ConditionalExpression(Expression exp1, Expression exp2, Expression exp3) {
		super();
		this.exp1 = exp1;
		this.exp2 = exp2;
		this.exp3 = exp3;
	}

	@Override
	public String toString() {
		return "ConditionalExpression("+exp1+", "+exp2+", "+exp3+")";
	}

	@Override
	public int eval() {
		if(exp1.eval()>0){
			return exp2.eval();
		}
		else{
			return exp3.eval();
		}
	}

}
