package br.unb.fas.expressiontree.structure;

/**
 * @class CompositeAddNode
 *
 * @brief	Um nó contendo os filhos da esquerda e direita. 
 * 			O significado deste nó é left + right. Ele exerce
 * 			o papel do "Composite" no padrão "Composite".
 */
public class CompositeAddNode extends CompositeBinaryNode
{
    /** Ctor */
    public CompositeAddNode(ComponentNode left,
                            ComponentNode right)
    {
        super(left, right);
    }


}
