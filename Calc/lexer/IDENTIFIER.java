package lexer;

public class IDENTIFIER extends Token {
	String string;
	
	public IDENTIFIER(char character){
		this.string = ""+character;
	}
	
	public IDENTIFIER(String string){
		this.string = string;
	}
	
	public String toString(){
		return "IDENTIFIER("+this.string+")";
	}
}
