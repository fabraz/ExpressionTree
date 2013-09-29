package br.unb.fas.expressiontree.structure;

/**
 * @class CompositeNegateNode
 *
 * @brief Um nó contendo somente o filho da direita. 
 * 			O significado deste nó é -right (ex: -5, -7, etc.). 
 * 			Ele exerce o papel do "Composite" no padrão "Composite".
 * 
 */ 
public class CompositeNegateNode extends CompositeUnaryNode
{
    /** Ctor */
    public CompositeNegateNode(ComponentNode right)
    {
        super(right);
    }
}
