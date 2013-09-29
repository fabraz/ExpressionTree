package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.LeafNode;

/**
 * @class Number
 *
 * @brief Define um nó que analisa sintaticamente expressões terminais numéricas.
 */
class Number extends Symbol
{
	
    /** Precedência de número. */
    final static int numberPrecedence = 4;
    
    /** Valor do Número. */
    public int item;

    /** Ctor */
    public Number(String input)
    {
        super(null, null, numberPrecedence);
        item = Integer.parseInt(input);
    }

    /** Ctor */
    public Number(int input)
    {
        super(null, null, numberPrecedence);
        item = input;
    }

    /** 
     * Adiciona numberPrecedence ao accumulatedPrecedence corrente.
     */
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence = 
            numberPrecedence + accumulatedPrecedence;
    }

    /** 
     * Metodo para retornar o nível de procedência (valores elevados significam maior precedência).
     */
    public int precedence()
    {
        return precedence;
    }

    /** Constroi um @a LeadNode. */
    ComponentNode build()
    {
        return new LeafNode(item);
    }
}

