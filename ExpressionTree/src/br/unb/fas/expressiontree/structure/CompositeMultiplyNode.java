package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class CompositeMultiplyNode
 *
 * @brief 	Um n� contendo os filhos da esquerda e direita. 
 * 			O significado deste n� � left * right. Ele exerce
 * 			o papel do "Composite" no padr�o "Composite".
 */
public class CompositeMultiplyNode extends CompositeBinaryNode
{
    /** Ctor */
    public CompositeMultiplyNode(ComponentNode left,
                                 ComponentNode right)
    {
        super(left, right);
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
