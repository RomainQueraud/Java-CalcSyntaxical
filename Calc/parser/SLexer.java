package parser;

import java.io.IOException;

import lexer.Lexer;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class SLexer {
	private static Lexer lexer;
	
	public static void init(String filename) throws IOException{
		lexer = new Lexer(filename);
	}
	
	public static Token getToken() throws UnexpectedCharacter, IOException{
		return lexer.getToken();
	}
}
