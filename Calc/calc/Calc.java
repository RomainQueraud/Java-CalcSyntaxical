package calc;

import java.io.IOException;

import lexer.Token;
import lexer.UnexpectedCharacter;
import parser.Expression;
import parser.SLexer;

public class Calc {
	public static void main(String[] args) throws UnexpectedCharacter, IOException {
		SLexer.init(args[0]);
		Token t = SLexer.getToken();
		Expression exp = Expression.parse(t);
		System.out.println(exp);
	}
}
