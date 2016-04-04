package parser;

import java.io.IOException;

import calc.Memoire;
import lexer.IDENTIFIER;
import lexer.LPAR;
import lexer.RPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class Definition extends AST{
	
	public IDENTIFIER variable;
	public Expression expression;
	
	public Definition(IDENTIFIER variable, Expression expression){
		this.variable = variable;
		this.expression = expression;
	}
	
	public static Definition parse(Token t) throws UnexpectedCharacter, IOException{
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			return Definition.parseLPAR(t2);
		}
		else{
			throw new RuntimeException();
		}
	}
	
	public static Definition parseLPAR(Token t2) throws UnexpectedCharacter, IOException{
		if(t2.toString().equals("=")){
			Token t3 = SLexer.getToken();
			IDENTIFIER ident = (IDENTIFIER) t3;
			Token t4 = SLexer.getToken();
			Expression exp2 = Expression.parse(t4);
			Token t5 = SLexer.getToken();
			if(t5 instanceof RPAR){
				return new Definition(ident, exp2);
			}
			else{
				throw new RuntimeException();
			}
		}
		else throw new RuntimeException();
	}

	@Override
	public String toString() {
		return "Definition("+this.variable+", "+this.expression+")";
	}
	
	public int eval(){
		int eval = this.expression.eval();
		Memoire.variables.put(this.variable.string, eval);
		return eval;
	}
}
