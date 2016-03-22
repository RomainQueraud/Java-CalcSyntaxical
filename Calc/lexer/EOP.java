package lexer;

public enum EOP {
	
	PLUS("+"),
	MINUS("-"),
	TIMES("*"),
	DIVIDE("/"),
	LESS("<"),
	EQUAL("=");
	
	private String name = "";
	   
	//Constructeur
	EOP(String name){
		this.name = name;
	}
   
	public String toString(){
		return name;
	}
}
