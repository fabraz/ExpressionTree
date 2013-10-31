package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class CompositeNegateNode
 *
 * @brief Um nodo contendo somente o filho da direita. 
 * 			O significado deste nodo � -right (ex: -5, -7, etc.). 
 * 			Ele exerce o papel do "Composite" no padrao "Composite".
 * 
 */ 
public class CompositeNegateNode extends CompositeUnaryNode
{
    /** Ctor */
    public CompositeNegateNode(ComponentNode right)
    {
        super(right);
    }
    
    /** Retorna o caractere imprim�vel do node. */
    public int item()
    {
        return '-';
    }
    
    /* 
     * Define a operacao @a accept() usada pelo padrao visitor. 
     */
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }
    
    
}
