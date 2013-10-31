package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class ComponentNode
 *
 * @brief 	Uma classe abstrata define uma implementação simples de um 
 * 			nó de uma árvore de expressão. Esta classe assume o papel 
 * 			do "Component" no padrão composite.
 * 			Os métodos nesta classe não são definidos como virtuais 
 * 			puros, de modo que as subclasses no padrão Composite não 
 * 			necessitam implementar os métodos que não possuem relação 
 * 			com elas The
 */
public abstract class ComponentNode
{
    /** Este método é um método não operacional na nesta classe abstrata. */
    public int item()
    {
        throw new UnsupportedOperationException("Método ComponentNode::item() invocado de forma inapropriada.");
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
     * Aceita um visitor para realizar alguma ação no item nodo
     * de maneira completamente arbitrária (lança uma exceção, 
     * caso seja invocado diretamente).
     */
    void accept (Visitor visitor)
    {
        throw new UnsupportedOperationException("ComponentNode::accept() invocado de forma inapropriada");
    }
    
    
  
}
