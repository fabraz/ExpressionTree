package br.unb.fas.expressiontree.structure;

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
}
