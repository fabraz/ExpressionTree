package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;

/**
 * @class UnaryOperator
 *
 * @brief Define um nó na árvore sintática para expressões não terminais unárias.
 */
public abstract class UnaryOperator extends Symbol
{
    /** Ctor */
    UnaryOperator(Symbol right,
                  int precedence)
    {
        super(null, right, precedence);
    }

    /** Método abstrato para construção de um nó @a UnaryOperator. */
    abstract ComponentNode build();
}
