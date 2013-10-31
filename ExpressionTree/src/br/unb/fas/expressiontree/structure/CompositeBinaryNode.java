package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class CompositeBinaryNode
 *
 * @brief Define um nó esqueda e direita (via herança).  Ele exerce
 * 		o papel de "Composite" no padrão Composite.
 */
public class CompositeBinaryNode extends CompositeUnaryNode
{
    /** Referência a filho da esquerda. */
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
     * Aceita um visitor para realizar alguma ação no item nodo
     * de maneira completamente arbitrária (lança uma exceção, 
     * caso seja invocado diretamente).
     */
    void accept (Visitor visitor)
    {
        throw new UnsupportedOperationException("ComponentNode::accept() invocado de forma inapropriada");
    }
    
    
}
