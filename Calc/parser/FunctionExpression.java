package parser;

import java.util.ArrayList;

import lexer.IDENTIFIER;

public class FunctionExpression extends Expression {
	
	public IDENTIFIER identifier;
	public ArrayList<Expression> expressions;

	public FunctionExpression(IDENTIFIER identifier, ArrayList<Expression> expressions) {
		super();
		this.identifier = identifier;
		this.expressions = expressions;
	}

	@Override
	public String toString() {
		String e = "";
		for(int i=0; i<this.expressions.size(); i++)
			e+=", "+this.expressions.get(i);
		return "FunctionExpression("+this.identifier+e+")";
	}

	@Override
	public int eval() { //TODO Sera l'appel de la fonction, donc il faudra aller la chercher en mémoire
		// TODO Auto-generated method stub
		return 0;
	}

}
