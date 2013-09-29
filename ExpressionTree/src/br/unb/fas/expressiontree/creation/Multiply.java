package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.CompositeMultiplyNode;

/**
 * @class Multiply
 *
 * @brief Define um nó da árvore sintática para operador binário não terminal multiply.
 */
class Multiply extends Operator
{
    /** Ctor */
    public Multiply()
    {
        super(null, null, mulDivPrecedence);
    }

    /** Adiciona a precedência ao valor corrente. */
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence =
            mulDivPrecedence + accumulatedPrecedence;
    }

    /** Método para contrução do nó @a Multiple. */
    ComponentNode build()
    {
        return new CompositeMultiplyNode(left.build(),
                                         right.build());
    }

    /** Retorna a precedência. */
    public int precedence()
    {
        return precedence;
    }
}