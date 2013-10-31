package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class ComponentNode
 *
 * @brief 	Uma classe abstrata define uma implementa��o simples de um 
 * 			n� de uma �rvore de express�o. Esta classe assume o papel 
 * 			do "Component" no padr�o composite.
 * 			Os m�todos nesta classe n�o s�o definidos como virtuais 
 * 			puros, de modo que as subclasses no padr�o Composite n�o 
 * 			necessitam implementar os m�todos que n�o possuem rela��o 
 * 			com elas The
 */
public abstract class ComponentNode
{
    /** Este m�todo � um m�todo n�o operacional na nesta classe abstrata. */
    public int item()
    {
        throw new UnsupportedOperationException("M�todo ComponentNode::item() invocado de forma inapropriada.");
    }

    /** Retorna o filho direito (returna 0 se invocado diretamente). */
    public ComponentNode right() 
    {
        return null;
    }
  
    /** Returna o filho esquerdo (returna 0 se invocado diretamente). */
    public ComponentNode left() 
    {
        return null;
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
