package parser;

import java.io.IOException;

import calc.Memoire;
import lexer.IDENTIFIER;
import lexer.LPAR;
import lexer.RPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class Definition extends AST{
	
	public Variable variable;
	public Expression expression;
	
	public Definition(Variable variable, Expression expression){
		this.variable = variable;
		this.expression = expression;
	}
	
	/*
	 * Retourne true si on a bien définition
	 * TODO Il faut que si on est pas dans une Définition, 
	 * 	on remette dans le lexer les caractères à relire
	 */
	public static boolean parse(Token t) throws UnexpectedCharacter, IOException{
		boolean ret = false;
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			if(t2.toString().equals("=")){
				Token t3 = SLexer.getToken();
				IDENTIFIER ident = (IDENTIFIER) t3;
				Token t4 = SLexer.getToken();
				Expression exp2 = Expression.parse(t4);
				Token t5 = SLexer.getToken();
				if(t5 instanceof RPAR){
					Memoire.variables.put(ident.string, exp2.eval());
					ret = true;
				}
			}
		}
		return ret;
	}

	@Override
	public String toString() {
		return "Definition("+this.variable+", "+this.expression+")";
	}
}
