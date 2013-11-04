package br.unb.fas.expressiontree.creation;

import java.util.Stack;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class Interpreter
 *
 * @brief Analisa sintaticamente a express�o fornecida como entrada
 * 		  e constroi a expression tree a partir da �rvore sint�tica.
 * 	 	  Esta classe exerce o papel de "Interpreter" no padr�o Interpreter
 * 		  adicionado da preced�ncia dos operadores/operandos para guiar 
 * 		  a cria��o da �rvore sint�tica. O padr�o Builder � usada tamb�m 
 * 		  para construir os n�s correspondentes na expression tree.
 */
public class Interpreter
{
    /** Armazena os n�meros com m�ltiplos d�gitos. */
    private int multiDigitNumbers;

    /** Armazena no �ltimo simbolo. */
    private Symbol lastValidInput;

    /** Rastreia a preced�ncia da express�o. */
    private int accumulatedPrecedence;

    /** Preced�ncia de parenteses. */
    final static int parenPrecedence = 5;

    /**
     * Armazena as vari�veis e seus valores para uso do Interpreter.
     */
    private SymbolTable symbolTable =  new SymbolTable();

    /** 
     * M�todo de acesso. 
     */
    public SymbolTable symbolTable()
    {
        return symbolTable;
    }


    /**
     * Este metodo primeiramente converte a string na �rvore sint�tica e ent�o
     * constroi uma expression tree a partir da arvore sintatica. A sua
     * implementa��o segue o padrao Template Method.
     */
    public ExpressionTree interpret(String inputExpression)
    {
        /** A �rvore sint�tica � implementada numa pilha. */
        Stack<Symbol> parseTree = buildParseTree(inputExpression);

        /** 
         * Caso a �rvore sint�tica possua um elemento � realizada 
         * a constru��o da ExpressionTree a partir da �rvore sint�tica.
         */
        if (!parseTree.empty())
            {
                /* Constroi a ExpressionTress a partir da �rvore sint�tica. */
                return buildExpressionTree (parseTree);
            }
        else
            /** Esse fluxo indica que n�o h� simbolos. */
            return new ExpressionTree(null);
    }

    public Stack<Symbol> buildParseTree (String inputExpression)
    {
        Stack<Symbol> parseTree = new Stack<Symbol>();

        // Guarda a �ltima entrada v�lida, que � �til para 
        // lidar com operadores un�rios de nega��o.
        lastValidInput = null;

        boolean handled = false;

        /** Inicializa alguns membros de dados com os seus valores default. */
        accumulatedPrecedence = 0;
        multiDigitNumbers = 0;

        for(int index = 0;
            index < inputExpression.length();
            ++index)
            {
                /** 
                 * Localiza o pr�ximo sibolo na entrada e o coloca na �rvore sint�tica, 
                 * de acordo com a sua preced�ncia.
                 */
                parseTree = parseNextSymbol(inputExpression,
                                            index,
                                            handled,
                                            parseTree);

                if(multiDigitNumbers > index)
                    index = multiDigitNumbers;
            }

        return parseTree;
    }


    /** 
     * Invoca a constru��o recursiva da ExpressionTrees, come�ando pelo 
     * simbolo root, que deve ser o �nico item na linked list.
     * O padr�o Builder � usado em cada n� para criar a subclasse 
     * apropriada do @a ComponentNode.
     */
    protected ExpressionTree buildExpressionTree(Stack<Symbol> parseTree)
    {
        /** Deve haver apenas um elemento left na pilha! */
        assert (parseTree.size() == 1);
        return new ExpressionTree(parseTree.peek().build());
    }

    /** Analisa sintaticamente a pr�xima express�o terminal.*/
    private Stack<Symbol> parseNextSymbol
        (String inputExpression,
         int index,
         boolean handled,
         Stack<Symbol> parseTree)
    {
        handled = false;
        if(Character.isDigit(inputExpression.charAt(index)))
            {
                handled = true;
                parseTree = insertNumberOrVariable(inputExpression,
                                                   index,
                                                   parseTree,
                                                   false);
            }
        else if(Character.isLetterOrDigit(inputExpression.charAt(index)))
            {
                handled = true;
                parseTree = insertNumberOrVariable(inputExpression, 
                                                   index,
                                                   parseTree,
                                                   true);
            }
        else if(inputExpression.charAt(index) == '+')
            {
                handled = true;
                /** Opera��o de adi��o. */
                Add op = new Add();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insire a opera��o de acordo com o relacionamento esquerda-para-direita. 
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '-')
            {
                handled = true;

                Symbol op = null;
                /** Opera��o de Substra��o e Nega��o. */

                if (lastValidInput == null)
	            {
                        /** Nega��o. */
                        op = new Negate();
                        op.addPrecedence(accumulatedPrecedence);
	            }
                else
	            {
                        /** Subtra��o. */
                        op = new Subtract();
                        op.addPrecedence(accumulatedPrecedence);
	            }

                lastValidInput = null;

                /** 
                 * Insire a opera��o de acordo com o relacionamento esquerda-para-direita.
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '*')
            {
                handled = true;
                /** Opera��o de multiplica��o. */
                Multiply op = new Multiply();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insere a opera��o de acordo com o relacionamento de preced�ncia. 
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '/')
            {
                handled = true;
                /** Opera��od de divis�o. */
                Divide op = new Divide();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insere a opera��o de acordo com o relacionamento de preced�ncia.
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '(')
            {
                handled = true;
                parseTree = handleParentheses(inputExpression,
                                              index,
                                              handled,
                                              parseTree);
            }
        else if(inputExpression.charAt(index)== ' ' 
                || inputExpression.charAt(index) == '\n')
            {
                handled = true;
                /** Ignora espa�o em branco. */
            }

        return parseTree;
    }

    /** Insere um @a Number na �rvore sint�tica. */
    private Stack<Symbol> insertNumberOrVariable(String input,
                                                 int startIndex,
                                                 Stack<Symbol> parseTree,
                                                 boolean isVariable)
    {
        /** 
         * Funde todos os caracteres num�ricos em um �nico simbolo de Number
         * ex. '123' = int(123). O escopo de j deve estar fora do loop for
         */
        int endIndex = 1;

        /** 
         * Para essa declara��o if adicional: if input = 1, uma exce��o de valor 
         * limite � lan�ada como resultado da declara��o charAt().
         */
        if(input.length() > startIndex + endIndex)
            /** Localiza o final do n�mero. */
            for(;
                startIndex + endIndex < input.length () 
                    && Character.isDigit(input.charAt(startIndex + endIndex)); 
                ++endIndex)
                continue;

        Number number;

        if (isVariable)
            /** Localiza o valor em symbolTable. */
            number =
                new Number(symbolTable.get(input.substring(startIndex,
                                                           startIndex 
                                                           + endIndex)));
        else
            number =
                new Number(input.substring(startIndex,
                                           startIndex + endIndex));

        number.addPrecedence(accumulatedPrecedence);

        lastValidInput = number;

        /** 
         * Atualiza o starIndex para o �ltimo caractere num�rico. 
         * O ++startIndex atualizar� o startIndex no final do loop 
         * para a pr�xima verifica��o.
         */
        startIndex += endIndex - 1;
        multiDigitNumbers = startIndex;

        return insertSymbolByPrecedence(number, parseTree);
    }

    /** Insere um @a Symbol na �rvore sint�tica. */
    private Stack<Symbol> insertSymbolByPrecedence
        (Symbol symbol,
         Stack<Symbol> parseTree)
    {
        if(!parseTree.empty())
	    {
                /** 
                 * Se o �ltimo elemento for um n�mero, ent�o fa�a dele o seu left.
                 */
                Symbol parent = parseTree.peek();
                Symbol child = parent.right;

                if(child != null)
                    /** 
                     * Enquanto houver um filho de um pai, navegue observando o lado direito.
                     */
                    for(;
                        child != null 
                            && child.precedence () < symbol.precedence ();
                        child = child.right)
                        parent = child;

                if(parent.precedence () < symbol.precedence())
                    {
                        /** 
                         * o symbol left ser� o filho mais velho. Novos filhos do pai ser�o symbol. 
                         * Para permitir nega��es infinitas, deve-se avaliar a presen�a de operadores un�rios.
                         */
                        if(symbol.left == null)
                            symbol.left = child;

                        parent.right = symbol;
                    }
                else
                    {
                        /** 
                         * Temos duas possibilidades, ou a mesma preced�ncia ou preced�ncia inferior 
                         * que seu pai. O que significa coisas diferentes para opera��es un�rias.
                         * O operador un�rio mais recente possui preced�ncia mais elevada
                         */
                	
                        UnaryOperator up = new Negate();

                        if(symbol.getClass() == up.getClass())
                            {
                                for(;
                                    child != null 
                                        && child.precedence() == symbol.precedence();
                                     child = child.right)
                                    parent = child;

                                parent.right = symbol;
                            }
                        else
                            {
                                /**
                                 * O resto � avaliado da mesma forma.
                                 * Por exemplo, se tivermos 5 * 4 / , e
                                 * tivermos Mult(5,4) na parseTree, 
                                 * precisamos tornar o pai o n� da esquerda
                                 */
                                symbol.left = parent;
                                parseTree.pop();
                                parseTree.push(symbol);
                                parent = child;
                            }
                    }
	    }
        else
            parseTree.push(symbol);

        return parseTree;
    }

    private Stack<Symbol> handleParentheses
        (String inputExpression,
         int index,
         boolean handled,
         Stack<Symbol> masterParseTree)
    {
        /** 
         * A manipula��o de parentese se assemelha em muito a manipula��o da chamada interpret()
         * A diferen�a � que devemos nos preocupar sore como a chamada a fun��o lidar� com a sua masterParseTree
         */
        accumulatedPrecedence += parenPrecedence;
        Stack<Symbol> localParseTree =
            new Stack<Symbol>();

        handled = false;
        for(++index; 
            index < inputExpression.length(); 
            ++index)
	    {
                localParseTree = parseNextSymbol(inputExpression,
                                                 index,
                                                 handled,
                                                 localParseTree);

                if(multiDigitNumbers > index)
                    index = multiDigitNumbers;

                if (inputExpression.charAt(index) == ')')
                    {
                        handled = true;
                        accumulatedPrecedence -= parenPrecedence;
                        break;
                    }
	    }

        if(masterParseTree.size() > 0 && localParseTree.size() > 0)
	    {
                Symbol op = masterParseTree.peek();

                /** Se trata de um n� com dois filhos? */
                if(op != null)
                    masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(),
                                                 masterParseTree);
                else if(op == null)
                    /** Se trada de um n� un�rio (como nega��o)? */
                    masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(),
                                                 masterParseTree);
	    }
        else if (localParseTree.size () > 0)
            masterParseTree = localParseTree;

        return masterParseTree;
    }
}

