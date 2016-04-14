package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexer.LPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class Body extends AST{
	
public ArrayList<Definition> definitions;
public Expression expression;

	public Body(ArrayList<Definition> definitions, Expression expression){
		this.definitions = definitions;
		this.expression = expression;
	}

	
	public static Body parse(Token t, ArrayList<Definition> defs) throws UnexpectedCharacter, IOException{
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			return parseLPAR(t2, defs);
		}
		else{
			Expression exp = Expression.parse(t);
			return new Body(defs, exp);
		}
	}
	
	public static Body parseLPAR(Token t2, ArrayList<Definition> defs) throws UnexpectedCharacter, IOException{
		if(t2.toString().equals("=")){ //C'est une Definition
			Definition def = Definition.parseLPAR(t2);
			defs.add(def);
			return Body.parse(SLexer.getToken(), defs);
		}
		else{
			Expression exp = Expression.parseLPAR(t2);
			return new Body(defs, exp);
		}
	}

	@Override
	public String toString() {
		/*
		String d = "";
		for(int i=0; i<this.definitions.size(); i++)
			d+=this.definitions.get(i) + ", ";
		*/
		return "Body("+this.definitions+", "+this.expression+")";
	}


	@Override
	public int eval() {
		for(int i=0; i<this.definitions.size(); i++){
			this.definitions.get(i).eval();
		}
		return this.expression.eval();
	}
}
