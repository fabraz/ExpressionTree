package br.unb.fas.expressiontree.structure;

import br.unb.fas.expressiontree.traversal.Visitor;

/**
 * @class ExpressionTree
 *
 * @brief Interface para o padrão Composite que é usdo para conter os
 * 			nodos operador e operando para o padrão Composite 
 *			que é usado para conter todas os nodos operadores e 
 *			operandos na árvore de expressão. 
 *			Exerce papel de "Abstração" no padrão Bridge e delega ao 
 *			implementor apropriado que executa as operações da árvore
 *			de expressão
 */
public class ExpressionTree
{
    /** Implementor base. */
    protected ComponentNode root = null;

    /**
     * Construtor que recebe um @a Nodo * que contém todos os nodos na 
     * árvore de expressãothat.
     */
    public ExpressionTree(ComponentNode root)
    {
        this.root = root;
    }

    /** Retorna se a árvore está vazia. */
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

    /** Retorna o nó da esquerda. */
    public ExpressionTree left()
    {
        return new ExpressionTree(root.left());
    }

    /** Retorna o nó da direita. */
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
