package br.unb.fas.expressiontree.structure;

/**
 * @class CompositeUnaryNode
 *
 * @brief 	Define o filho direito (mas não o esquerdo) e, então, passa
 * 			a ser útil para operações unárias. Ela exerce o papel do
 * 			"Composite" no padrão Composite.
 */
public class CompositeUnaryNode extends ComponentNode
{
    /** Referência ao filho da direita. */
    private ComponentNode right;
	
    /** Ctor */
    public CompositeUnaryNode(ComponentNode right)
    {
        this.right = right;
 
    }
	
    /** Retorna o filho da direita. */
    public ComponentNode right() 
    {
        return this.right;
    }
}
