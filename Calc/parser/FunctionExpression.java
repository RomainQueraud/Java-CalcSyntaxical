package parser;

import java.util.ArrayList;

import lexer.IDENTIFIER;
import state.Stack;
import state.State;

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
		/*
		String e = "";
		for(int i=0; i<this.expressions.size(); i++)
			e+=", "+this.expressions.get(i);
		*/
		return "FunctionExpression("+this.identifier+", "+this.expressions+")";
	}

	@Override
	public int eval() {
		int ret;
		ArrayList<Integer> values = new ArrayList<Integer>();
		if(Stack.functions.get(Stack.functions.size()-1).containsKey(this.identifier.string)){
			Function function = Stack.functions.get(Stack.functions.size()-1).get(this.identifier.string);
			if(function.head.functionVariables.size() == this.expressions.size()){
				for(int i=0 ; i<function.head.functionVariables.size() ; i++){
					int value = this.expressions.get(i).eval();
					values.add(value);
					//on a besoin de faire tous les calculs avant de bind pour ne pas se m�langer les niveaux de variables
				}
				Stack.variables.add(new State<Integer>()); //Ajoute un niveau � la pile de recursion
				bindVariables(function, values);
				ret = function.body.eval();
				Stack.variables.remove(Stack.variables.size()-1);
			}
			else{ //Le nombre de parametre donn� n'est pas le m�me que celui demand�
				throw new RuntimeException("Erreur : variables en m�moire non correspondantes");
			}
		}
		else{
			throw new RuntimeException("Erreur : variable non d�finie");
		}
		return ret;
	}
	
	private void bindVariables(Function function, ArrayList<Integer> values){
		for(int i=0 ; i<function.head.functionVariables.size() ; i++){
			String name = function.head.functionVariables.get(i).string;
			int value = values.get(i);
			Stack.variables.get(Stack.variables.size()-1).bind(name, value);
		}
	}

}
