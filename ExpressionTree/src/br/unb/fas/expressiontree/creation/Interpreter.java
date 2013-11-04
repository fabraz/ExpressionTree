package br.unb.fas.expressiontree.creation;

import java.util.Stack;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class Interpreter
 *
 * @brief Analisa sintaticamente a expressão fornecida como entrada
 * 		  e constroi a expression tree a partir da árvore sintática.
 * 	 	  Esta classe exerce o papel de "Interpreter" no padrão Interpreter
 * 		  adicionado da precedência dos operadores/operandos para guiar 
 * 		  a criação da árvore sintática. O padrão Builder é usada também 
 * 		  para construir os nós correspondentes na expression tree.
 */
public class Interpreter
{
    /** Armazena os números com múltiplos dígitos. */
    private int multiDigitNumbers;

    /** Armazena no último simbolo. */
    private Symbol lastValidInput;

    /** Rastreia a precedência da expressão. */
    private int accumulatedPrecedence;

    /** Precedência de parenteses. */
    final static int parenPrecedence = 5;

    /**
     * Armazena as variáveis e seus valores para uso do Interpreter.
     */
    private SymbolTable symbolTable =  new SymbolTable();

    /** 
     * Método de acesso. 
     */
    public SymbolTable symbolTable()
    {
        return symbolTable;
    }


    /**
     * Este metodo primeiramente converte a string na árvore sintática e então
     * constroi uma expression tree a partir da arvore sintatica. A sua
     * implementação segue o padrao Template Method.
     */
    public ExpressionTree interpret(String inputExpression)
    {
        /** A árvore sintática é implementada numa pilha. */
        Stack<Symbol> parseTree = buildParseTree(inputExpression);

        /** 
         * Caso a árvore sintática possua um elemento é realizada 
         * a construção da ExpressionTree a partir da árvore sintática.
         */
        if (!parseTree.empty())
            {
                /* Constroi a ExpressionTress a partir da árvore sintática. */
                return buildExpressionTree (parseTree);
            }
        else
            /** Esse fluxo indica que não há simbolos. */
            return new ExpressionTree(null);
    }

    public Stack<Symbol> buildParseTree (String inputExpression)
    {
        Stack<Symbol> parseTree = new Stack<Symbol>();

        // Guarda a última entrada válida, que é útil para 
        // lidar com operadores unários de negação.
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
                 * Localiza o próximo sibolo na entrada e o coloca na árvore sintática, 
                 * de acordo com a sua precedência.
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
     * Invoca a construção recursiva da ExpressionTrees, começando pelo 
     * simbolo root, que deve ser o único item na linked list.
     * O padrão Builder é usado em cada nó para criar a subclasse 
     * apropriada do @a ComponentNode.
     */
    protected ExpressionTree buildExpressionTree(Stack<Symbol> parseTree)
    {
        /** Deve haver apenas um elemento left na pilha! */
        assert (parseTree.size() == 1);
        return new ExpressionTree(parseTree.peek().build());
    }

    /** Analisa sintaticamente a próxima expressão terminal.*/
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
                /** Operação de adição. */
                Add op = new Add();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insire a operação de acordo com o relacionamento esquerda-para-direita. 
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '-')
            {
                handled = true;

                Symbol op = null;
                /** Operação de Substração e Negação. */

                if (lastValidInput == null)
	            {
                        /** Negação. */
                        op = new Negate();
                        op.addPrecedence(accumulatedPrecedence);
	            }
                else
	            {
                        /** Subtração. */
                        op = new Subtract();
                        op.addPrecedence(accumulatedPrecedence);
	            }

                lastValidInput = null;

                /** 
                 * Insire a operação de acordo com o relacionamento esquerda-para-direita.
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '*')
            {
                handled = true;
                /** Operação de multiplicação. */
                Multiply op = new Multiply();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insere a operação de acordo com o relacionamento de precedência. 
                 */
                parseTree = insertSymbolByPrecedence(op, parseTree);
            }
        else if(inputExpression.charAt(index) == '/')
            {
                handled = true;
                /** Operaçãod de divisão. */
                Divide op = new Divide();
                op.addPrecedence(accumulatedPrecedence);

                lastValidInput = null;

                /** 
                 * Insere a operação de acordo com o relacionamento de precedência.
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
                /** Ignora espaço em branco. */
            }

        return parseTree;
    }

    /** Insere um @a Number na árvore sintática. */
    private Stack<Symbol> insertNumberOrVariable(String input,
                                                 int startIndex,
                                                 Stack<Symbol> parseTree,
                                                 boolean isVariable)
    {
        /** 
         * Funde todos os caracteres numéricos em um único simbolo de Number
         * ex. '123' = int(123). O escopo de j deve estar fora do loop for
         */
        int endIndex = 1;

        /** 
         * Para essa declaração if adicional: if input = 1, uma exceção de valor 
         * limite é lançada como resultado da declaração charAt().
         */
        if(input.length() > startIndex + endIndex)
            /** Localiza o final do número. */
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
         * Atualiza o starIndex para o último caractere numérico. 
         * O ++startIndex atualizará o startIndex no final do loop 
         * para a próxima verificação.
         */
        startIndex += endIndex - 1;
        multiDigitNumbers = startIndex;

        return insertSymbolByPrecedence(number, parseTree);
    }

    /** Insere um @a Symbol na árvore sintática. */
    private Stack<Symbol> insertSymbolByPrecedence
        (Symbol symbol,
         Stack<Symbol> parseTree)
    {
        if(!parseTree.empty())
	    {
                /** 
                 * Se o último elemento for um número, então faça dele o seu left.
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
                         * o symbol left será o filho mais velho. Novos filhos do pai serão symbol. 
                         * Para permitir negações infinitas, deve-se avaliar a presença de operadores unários.
                         */
                        if(symbol.left == null)
                            symbol.left = child;

                        parent.right = symbol;
                    }
                else
                    {
                        /** 
                         * Temos duas possibilidades, ou a mesma precedência ou precedência inferior 
                         * que seu pai. O que significa coisas diferentes para operações unárias.
                         * O operador unário mais recente possui precedência mais elevada
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
                                 * O resto é avaliado da mesma forma.
                                 * Por exemplo, se tivermos 5 * 4 / , e
                                 * tivermos Mult(5,4) na parseTree, 
                                 * precisamos tornar o pai o nó da esquerda
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
         * A manipulação de parentese se assemelha em muito a manipulação da chamada interpret()
         * A diferença é que devemos nos preocupar sore como a chamada a função lidará com a sua masterParseTree
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

                /** Se trata de um nó com dois filhos? */
                if(op != null)
                    masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(),
                                                 masterParseTree);
                else if(op == null)
                    /** Se trada de um nó unário (como negação)? */
                    masterParseTree = 
                        insertSymbolByPrecedence(localParseTree.peek(),
                                                 masterParseTree);
	    }
        else if (localParseTree.size () > 0)
            masterParseTree = localParseTree;

        return masterParseTree;
    }
}

