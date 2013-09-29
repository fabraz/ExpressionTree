package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;
import br.unb.fas.expressiontree.structure.CompositeDivideNode;

/**
 * @class Divide
 *
 * @brief Defines a node in the parse tree for the binary divide
 *        operator non-terminal expression.
 */
class Divide extends Operator
{
    /** Ctor */
    public Divide()
    {
        super(null, null, mulDivPrecedence);
    }

    /** Returns the current precedence. */
    public int precedence()
    {
        return precedence;
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence)
    {
        return precedence = 
            mulDivPrecedence + accumulatedPrecedence;
    }

    /** Method for building a @a Divide node. */
    ComponentNode build()
    {
        return new CompositeDivideNode(left.build(),
                                       right.build());
    }
}
