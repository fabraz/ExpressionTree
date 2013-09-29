package br.unb.fas.expressiontree.structure;

/**
 * @class CompositeDivideNode
 * 
 * @brief 	Um nó contendo os filhos da esquerda e direita. 
 * 			O significado deste nó é left / right. Ele exerce
 * 			o papel do "Composite" no padrão "Composite"..
 */
public class CompositeDivideNode extends CompositeBinaryNode
{
    /** Ctor */
    public CompositeDivideNode(ComponentNode left,
                               ComponentNode right)
    {
        super(left, right);
    }

}
