package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.CompositeSubtractNode;


/**
 * @class Subtract
 *
 * @brief Defines a node in the parse tree for the binary subtract
 *        operator non-terminal expression.
 */
class Subtract extends Operator
{
    /** Ctor */
    public Subtract()
    {
        super(null, null, addSubPrecedence);
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence =
            addSubPrecedence + accumulatedPrecedence;
    }

    /** Method for building a @a Subtract node. */
    ComponentNode build()
    {
        return new CompositeSubtractNode(left.build(),
                                         right.build());
    }

    /** Returns the current precedence. */
    public int precedence()
    {
        return precedence;
    }
}

