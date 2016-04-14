package parser;

import calc.Memoire;
import state.Stack;

public class Variable extends Expression {
	public String value;
	
	public Variable(String value){
		this.value = value;
	}

	@Override
	public String toString() {
		return "Variable("+this.value+")";
	}

	@Override
	public int eval() {
		if(Stack.variables.get(Stack.variables.size()-1).containsKey(this.value)){
			return Stack.variables.get(Stack.variables.size()-1).get(this.value);
		}
		else{
			throw new RuntimeException("Erreur : Variable '"+this.value+"' non définie");
		}
	}
}
