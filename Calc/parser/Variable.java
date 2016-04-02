package parser;

import calc.Memoire;

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
		if(Memoire.variables.containsKey(this.value)){
			return Memoire.variables.get(this.value);
		}
		else{
			throw new RuntimeException();
		}
	}
	

}
