package br.unb.fas.expressiontree;

import br.unb.fas.expressiontree.creation.Interpreter;
import br.unb.fas.expressiontree.structure.ExpressionTree;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Interpreter interpreter = new Interpreter();
		ExpressionTree ET = interpreter.interpret("-(35+4*(2+7/(5-14))");

	}

}
