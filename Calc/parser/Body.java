package parser;

import java.util.ArrayList;

import lexer.Token;

public class Body extends AST{
	
public static ArrayList<AST> list = new ArrayList<AST>();
	
	public static void parse(Token t){
		/*
		//TODO Il faut trouver un moyen de diff�rencier les d�finitions et les expressions
		while(t instanceof Definition){
			
		}
		*/
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
