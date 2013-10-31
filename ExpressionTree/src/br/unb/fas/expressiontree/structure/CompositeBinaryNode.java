package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class CompositeBinaryNode
 *
 * @brief Define um n� esqueda e direita (via heran�a).  Ele exerce
 * 		o papel de "Composite" no padr�o Composite.
 */
public class CompositeBinaryNode extends CompositeUnaryNode
{
    /** Refer�ncia a filho da esquerda. */
    private ComponentNode left;
  
    /** Ctor */
    public CompositeBinaryNode(ComponentNode left,
                               ComponentNode right)
    {
        super(right);
        this.left = left;
    }

    /** Retorna o filho da esquerda. */
    public ComponentNode left()
    {
	return left;
    }
    
    /**
     * Aceita um visitor para realizar alguma a��o no item nodo
     * de maneira completamente arbitr�ria (lan�a uma exce��o, 
     * caso seja invocado diretamente).
     */
    void accept (Visitor visitor)
    {
        throw new UnsupportedOperationException("ComponentNode::accept() invocado de forma inapropriada");
    }
    
    
}
