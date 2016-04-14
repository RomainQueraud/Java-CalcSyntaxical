package lexer;

public class UnexpectedCharacter extends Exception {
	public UnexpectedCharacter(int i){
		super("Erreur : unexpected character : ascii " + i + " - " + (char)i);
	}

}
