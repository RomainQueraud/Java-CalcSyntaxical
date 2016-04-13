package parser;

import java.io.IOException;
import java.util.ArrayList;

import lexer.IDENTIFIER;
import lexer.LPAR;
import lexer.Token;
import lexer.UnexpectedCharacter;

public class Program extends AST {
	
	public ArrayList<Function> functions;
	public Body body;

	public Program(ArrayList<Function> functions, Body body) {
		super();
		this.functions = functions;
		this.body = body;
	}
	
	public static Program parse(Token t, ArrayList<Function> funs) throws UnexpectedCharacter, IOException{
		if(t instanceof LPAR){
			Token t2 = SLexer.getToken();
			if(t2 instanceof IDENTIFIER && ((IDENTIFIER)t2).string.equals("define")){
				funs.add(Function.parseLPAR(t2));
				return parse(SLexer.getToken(), funs);
			}
			else{
				Body b = Body.parseLPAR(t2, new ArrayList<Definition>());
				return new Program(funs, b);
			}
		}
		else{
			Body b = Body.parse(t, new ArrayList<Definition>());
			return new Program(funs, b);
		}
	}

	@Override
	public String toString() {
		String d = "";
		for(int i=0; i<this.functions.size(); i++)
			d+=this.functions.get(i) + ", ";
		return "Program("+d+this.body+")";
	}

	@Override
	public int eval() {
		for(int i=0; i<this.functions.size(); i++){
			this.functions.get(i).eval();
		}
		return this.body.eval();
	}

}
