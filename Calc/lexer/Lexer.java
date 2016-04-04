package lexer;
import java.util.*;
import java.io.*;

public class Lexer {
	private FileReader in;
	private int i; // current ASCII character (coded as an integer)
	
	public Lexer(String filename) throws IOException {
		File file = new File(filename);
		try {
			in = new FileReader(file);
			i = in.read(); // initialize lexer
		} catch (FileNotFoundException e) {
			System.err.println("File : " + filename + " not found");
			throw e; // pass the exception up the stack
		} catch (IOException e){
			in.close(); // close the reader
			throw e; // pass the exception up the stack
		}
	}
	
	public List<Token> lex() throws UnexpectedCharacter, IOException {
		// return the list of tokens recorded in the file
		List<Token> tokens = new ArrayList<Token>();
		
		try {
			Token token = getToken();
	
			while (! (token instanceof EOF)) {
				tokens.add(token);
				token = getToken();
			}
			tokens.add(token); // this is the EOF token
		} catch (IOException e){
				in.close(); // close the reader
				throw e; // pass the exception up the stack
		}
		return tokens;
	}
	
	public Token getToken() throws UnexpectedCharacter, IOException {
		switch (i){
		case -1 : 
			in.close();
			return new EOF();
		case ' ' :
			i=in.read();
			return this.getToken();
		case 13: //retour charriot
			i=in.read();
			return this.getToken();
		case 10: //line feed, qui est la suite du retour charriot
			i=in.read();
			return this.getToken();
		case '=' : 
			i=in.read();
			if(i=='='){
				i=in.read();
				return new OP(EOP.COMPARE);
			}
			return new OP(EOP.EQUAL);
		case '+' :
			i=in.read();
			return new OP(EOP.PLUS);
		case '-' :
			i=in.read();
			return new OP(EOP.MINUS);
		case '*' :
			i=in.read();
			return new OP(EOP.TIMES);
		case '/' :
			i=in.read();
			return new OP(EOP.DIVIDE);
		case '<' :
			i=in.read();
			return new OP(EOP.LESS);
		case '(' : 
			i=in.read(); //Puisqu'on a reconnu une parenthèse, on passe au caractère suivant
			return new LPAR();
		case ')' :
			i=in.read();
			return new RPAR();
		case '0' :
			i=in.read();
			return new LITERAL(0);
		default : 
			if('0'<=i && i<='9'){
				int nb=0;
				while('0'<=i && i<='9'){
					nb*=10; //Pour rajouter un 0 avant d'aditionner le nouveau chiffre
					nb += i - '0';
					i = in.read();
				}
				return new LITERAL(nb);
			}
			else if ('a'<=i && i<='z'){
				String s="";
				while('a'<=i && i<='z'){
					s+=(char)i;
					i=in.read();
				}
				if(s.equals("if")) return new IF();
				else return new IDENTIFIER(s);
			}
			else
				throw new UnexpectedCharacter(i);
		}
	}
}


