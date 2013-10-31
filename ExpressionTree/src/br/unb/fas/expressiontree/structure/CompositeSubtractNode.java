package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class CompositeSubtractNode
 *
 * @brief Um nó contendo os filhos da esquerda e direita. 
 * 			O significado deste nó é left - right. Ele exerce
 * 			o papel do "Composite" no padrão "Composite".
 */
public class CompositeSubtractNode extends CompositeBinaryNode
{
    /** Ctor */
    public CompositeSubtractNode(ComponentNode left,
                                 ComponentNode right)
    {
        super(left, right);
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
