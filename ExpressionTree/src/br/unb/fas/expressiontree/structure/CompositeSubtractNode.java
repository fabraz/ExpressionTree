package br.unb.fas.expressiontree.structure;

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

}
