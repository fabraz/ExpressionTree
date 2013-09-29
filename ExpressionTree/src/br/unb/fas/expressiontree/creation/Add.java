package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.CompositeAddNode;

/**
 * @class Add
 *
 * @brief Define um nó na árvore sintática o operador não terminal binário add.
 */
class Add extends Operator
{

    /** Ctor */
    public Add()
    {
        super(null, null, addSubPrecedence);
    }

    /** Adiciona a precedência ao valor corrente. */
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence =
            addSubPrecedence + accumulatedPrecedence;
    }

    /** Método para construir um nó @a Add. */
    ComponentNode build()
    {
        return new CompositeAddNode(left.build(),
                                    right.build());
    }

    /** Retorna a procedência corrente. */
    public int precedence()
    {
        return precedence;
    }
}
