package parser;

import java.io.IOException;
import java.util.ArrayList;

import lexer.IDENTIFIER;
import lexer.LPAR;
import lexer.RPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class Head extends AST{
	
	public IDENTIFIER functionName;
	public ArrayList<IDENTIFIER> functionVariables;
	
	public Head(IDENTIFIER functionName, ArrayList<IDENTIFIER> functionVariables) {
		super();
		this.functionName = functionName;
		this.functionVariables = functionVariables;
	}

	public static Head parse(Token t) throws UnexpectedCharacter, IOException{
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			if(t2 instanceof IDENTIFIER){
				Token t3 = SLexer.getToken();
				ArrayList<IDENTIFIER> vars = new ArrayList<IDENTIFIER>();
				while(!(t3 instanceof RPAR)){
					if(t3 instanceof IDENTIFIER){
						vars.add((IDENTIFIER)t3);
						t3 = SLexer.getToken();
					}
					else throw new RuntimeException("Erreur : Identifieur non reconnu");
				}
				return new Head((IDENTIFIER)t2, vars);
			}
			else throw new RuntimeException("Erreur : Identifieur non reconnu");
		}
		else throw new RuntimeException("Erreur : Parenthèse gauche manquante");
	}

	@Override
	public String toString() {
		String d = "";
		for(int i=0; i<this.functionVariables.size(); i++)
			d+=", "+this.functionVariables.get(i);
		return "Head("+d+this.functionVariables+")";
	}

	@Override
	public int eval() {
		return 0;
	}

}
