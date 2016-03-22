package lexer;

public class LITERAL extends Token	{
	public int value;
	
	public LITERAL(int value){
		this.value = value;
	}
	
	public String toString(){
		return "LITERAL("+this.value+")";
	}
}
