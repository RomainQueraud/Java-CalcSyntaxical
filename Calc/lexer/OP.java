package lexer;

public class OP extends Token {
	public EOP op;
	
	public String toString(){
		return op.toString();
	}
	
	public OP(EOP op){
		this.op = op;
	}
}
