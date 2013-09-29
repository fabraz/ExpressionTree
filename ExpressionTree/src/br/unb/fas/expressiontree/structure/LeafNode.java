package br.unb.fas.expressiontree.structure;


/**
 * @class LeafNode
 * 
 * @brief Define o nó terminal do tipo inteiro.  Ele exerce o papel de folha
 * 		no padrão Composite
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

    /* Retorna o item armazenado no nó. */
    public int item() 
    {
        return item;
    }
}
