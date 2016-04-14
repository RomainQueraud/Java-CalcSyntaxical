package parser;

import java.io.IOException;
import java.util.ArrayList;

import lexer.IDENTIFIER;
import lexer.LPAR;
import lexer.RPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;
import state.Stack;

public class Function extends AST {
	
	public Head head;
	public Body body;

	public Function(Head head, Body body) {
		super();
		this.head = head;
		this.body = body;
	}

	public static Function parse(Token t) throws UnexpectedCharacter, IOException{
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			return parseLPAR(t2);
		}
		else{
			throw new RuntimeException("Erreur : Parenthèse gauche manquante");
		}
	}
	
	public static Function parseLPAR(Token t2) throws UnexpectedCharacter, IOException{
		if(t2 instanceof IDENTIFIER && ((IDENTIFIER) t2).string.equals("define")){
			Token t3 = SLexer.getToken();
			Head h = Head.parse(t3);
			Token t4 = SLexer.getToken();
			Body b = Body.parse(t4, new ArrayList<Definition>());
			Token t5 = SLexer.getToken();
			if(t5 instanceof RPAR)
				return new Function(h, b);
			else throw new RuntimeException("Erreur : Parenthèse droite manquante");
		}
		else{
			throw new RuntimeException("Erreur : 'define' manquant");
		}
	}
	
	@Override
	public String toString() {
		return "Function("+head+", "+body+")";
	}

	@Override
	public int eval() {
		String name = this.head.functionName.string;
		if(Stack.functions.get(Stack.functions.size()-1).containsKey(name)){
			throw new RuntimeException("Erreur : fonction déjà définie");
		}
		Stack.functions.get(Stack.functions.size()-1).bind(name, this);
		return 0;
	}
}
