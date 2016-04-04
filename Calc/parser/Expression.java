package parser;

import java.io.IOException;

import lexer.*;

public abstract class Expression extends AST { //Abstract Syntax Tree
	//Arbre de syntaxe abstraite
	
	
	public static Expression parse(Token t) throws UnexpectedCharacter, IOException{
		if(t instanceof LITERAL){
			return new Literal(((LITERAL)t).value);
		}
		else if (t instanceof IDENTIFIER){
			return new Variable(((IDENTIFIER)t).string);
		}
		else if(t.toString().equals("-")){
			Token t2 = SLexer.getToken();
			Expression exp = Expression.parse(t2);
			return new UnaryMinus(exp);
		}
		else if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			return Expression.parseLPAR(t2);
		} else throw new RuntimeException();
	}
	
	public static Expression parseLPAR(Token t2) throws UnexpectedCharacter, IOException{
		if(t2 instanceof IF){
			Expression exp1 = Expression.parse(SLexer.getToken());
			Expression exp2 = Expression.parse(SLexer.getToken());
			Expression exp3 = Expression.parse(SLexer.getToken());
			if(SLexer.getToken() instanceof RPAR)
				return new ConditionalExpression(exp1, exp2, exp3);
			else throw new RuntimeException();
		}
		else if(t2 instanceof OP){
			Token t3 = SLexer.getToken();
			Expression exp1 = Expression.parse(t3);
			Token t4 = SLexer.getToken();
			if(t4 instanceof RPAR){
				if(t2.toString().equals("-")){
					//Sinon on pourrait caster t2 en OP et tester si EOP.Minus == t2.op
					return new UnaryMinus(exp1);
				}
				else throw new RuntimeException();
			}
			else{
				Expression exp2 = Expression.parse(t4);
				Token t5 = SLexer.getToken();
				if(t5 instanceof RPAR){
					return new BinaryExpression((OP) t2, exp1, exp2);
					//TODO Verifier que le cast de t2 en OP est légal et faire tous les else ThrowException
				}
				else throw new RuntimeException();
			}
		}
		else throw new RuntimeException();
	}
}
