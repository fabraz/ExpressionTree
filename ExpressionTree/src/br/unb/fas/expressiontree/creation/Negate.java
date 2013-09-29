package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.CompositeNegateNode;

/**
 * @class Negate
 *
 * @brief Define um nó na árvore sintática para operadores não 
 * 		terminais unários menos.
 */
class Negate extends UnaryOperator
{
    /** Precedência do operador '-' (negação). */
    final static int negatePrecedence = 3;

	
    /** Ctor */
    public Negate()
    {
        super(null, negatePrecedence);
    }

    /**Adicione precedência ao valor corrente*/
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence =
            negatePrecedence + accumulatedPrecedence;
    }

    /** Método para cosntrução do nó @a Negate.*/
    ComponentNode build()
    {
        return new CompositeNegateNode(right.build());
    }

    /** Retorna a procedencia corrente. */
    public int precedence()
    {
        return precedence;
    }
}
