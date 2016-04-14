package calc;

import java.io.IOException;
import java.util.ArrayList;

import lexer.EOF;
import lexer.Token;
import lexer.UnexpectedCharacter;
import parser.Body;
import parser.Definition;
import parser.Expression;
import parser.Function;
import parser.Program;
import parser.SLexer;
import state.Stack;
import state.State;

public class Calc {
	public static void main(String[] args) throws UnexpectedCharacter, IOException {
		
		SLexer.init(args[0]);
		Stack.variables.add(new State<Integer>()); //Initialise la mémoire
		Stack.functions.add(new State<Function>());
		
		Token t = SLexer.getToken();
		//Expression exp = Expression.parse(t); //Pour passer la piste verte
		//System.out.println(exp.eval());
		//Body body = Body.parse(t, new ArrayList<Definition>()); //Pour passer la piste bleue
		//System.out.println(body.eval());
		Program program = Program.parse(t, new ArrayList<Function>());
		if(SLexer.getToken() instanceof EOF)
			//System.out.println(program); //Pour afficher l'analyse
			System.out.println(program.eval());
		else
			throw new RuntimeException("Erreur : fin d'analyse effectuée, EOF non atteint");
	}
}
