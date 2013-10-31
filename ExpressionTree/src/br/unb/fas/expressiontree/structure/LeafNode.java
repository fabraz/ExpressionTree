package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;


/**
 * @class LeafNode
 * 
 * @brief Define o no terminal do tipo inteiro.  Ele exerce o papel de folha
 * 		no padrao Composite
 */
public class LeafNode extends ComponentNode
{
    /** Valor inteiro associado ao operando. */
    private int item;
  
    /* Ctor */
    public LeafNode(int item) 
    {
        this.item = item;
    }

    /* Ctor */
    public LeafNode(String item) 
    {
        this.item = Integer.parseInt(item);
    }

    /* Retorna o item armazenado no nodo. */
    public int item() 
    {
        return item;
    }
    
    /* 
     * Define a operacao @a accept() usada pelo padrao visitor. 
     */
    public void accept(Visitor visitor) 
    {
        visitor.visit(this);
    }
}
