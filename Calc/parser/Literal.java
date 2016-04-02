package parser;

public class Literal extends Expression {
public int value;
	
	public Literal(int value){
		this.value = value;
	}
	
	public String toString(){
		return "Literal("+this.value+")";
	}

	@Override
	public int eval() {
		return this.value;
	}
}
