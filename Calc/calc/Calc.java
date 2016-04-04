package calc;

import java.io.IOException;
import java.util.ArrayList;

import lexer.Token;
import lexer.UnexpectedCharacter;
import parser.Body;
import parser.Definition;
import parser.Expression;
import parser.SLexer;

public class Calc {
	public static void main(String[] args) throws UnexpectedCharacter, IOException {
		SLexer.init(args[0]);
		Token t = SLexer.getToken();
		//Expression exp = Expression.parse(t); //Pour passer la piste verte
		//System.out.println(exp.eval());
		Body body = Body.parse(t, new ArrayList<Definition>());
		System.out.println(body.eval());
		
	}
}
