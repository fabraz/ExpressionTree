package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class ExpressionTree
 *
 * @brief Interface para o padr�o Composite que � usdo para conter os
 * 			nodos operador e operando para o padr�o Composite 
 *			que � usado para conter todas os nodos operadores e 
 *			operandos na �rvore de express�o. 
 *			Exerce papel de "Abstra��o" no padr�o Bridge e delega ao 
 *			implementor apropriado que executa as opera��es da �rvore
 *			de express�o
 */
public class ExpressionTree
{
    /** Implementor base. */
    protected ComponentNode root = null;

    /**
     * Construtor que recebe um @a Nodo * que cont�m todos os nodos na 
     * �rvore de express�othat.
     */
    public ExpressionTree(ComponentNode root)
    {
        this.root = root;
    }

    /** Retorna se a �rvore est� vazia. */
    public boolean isNull()
    {
        return root == null;
    }

    /** Retorna o root. */
    public ComponentNode getRoot()
    {
        return root;
    }

    /** Retorna o item root. */
    public int item() throws Exception
    {
        return root.item();
    }

    /** Retorna o n� da esquerda. */
    public ExpressionTree left()
    {
        return new ExpressionTree(root.left());
    }

    /** Retorna o n� da direita. */
    public ExpressionTree right()
    {
        return new ExpressionTree(root.right());
    }
    
    
    /** Aceita um @a visitor. */
    public void accept(Visitor visitor)
    {
        root.accept(visitor);
    }

}
