package br.unb.fas.expressiontree.traversal;

import java.util.Iterator;
import java.util.Stack;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class PostOrderIterator
 * 
 * @brief Iterage por uma @a Tree usando a forma de percorrer in-order. 

 */
public class PostOrderIterator implements Iterator<ExpressionTree>
{
    /** Pilha da expression tree. */
    private Stack <ExpressionTree> stack = new Stack<ExpressionTree>();
	
    /** Ctor */
    public PostOrderIterator(ExpressionTree tree)
    {
        if(!tree.isNull()) 
            {
                stack.push(tree);
			
                /** 
                 * O c�digo comentado n�o funciona em um nodo operador un�rio sem 
                 * nodos a esquerda, mas a direita - ou pelo menos, existe algum 
                 * profundidade que n�o seja aprofundada.
                 */
                while(!tree.isNull())
                    {
                        if(!tree.right().isNull())
                            stack.push(tree.right());
                        if(!tree.left().isNull())
                            {
                                /** 
                                 * Se existir um esquerdo, atualize o atual, 
                                 * esse � o caso de todos que n�o sejam nega��es.
                                 */
                                stack.push(tree.left());
                                tree = tree.left();
                            }
                        else 
                            {
                                /** 
                                 * Se n�o existir esquero, ent�o 
                                 * current = current.right_ isso trata dos nos unarios, 
                                 * como as nega��es.
                                 */
                                tree = tree.right();
                            }
                    }
            }
    }
	
    /** Avan�a a pr�xima expression tree na pilha. */
    public ExpressionTree next() 
    {
        ExpressionTree result = stack.peek();
        if (!stack.isEmpty())
	    {
                /**
                 * � necess�rio retirar o n� da pulha antes de 
                 * empilhar o filho, ou ent�o revisitaremos esse n� no futuro
                 */

                ExpressionTree temp = stack.pop();

                if(!stack.isEmpty() 
                   && stack.peek().left().getRoot() != temp.getRoot()
                   && stack.peek().right().getRoot() != temp.getRoot())
                    {
                        temp = stack.peek();

                        while(!temp.isNull())
                            {
                                if(!temp.right().isNull())
                                    stack.push(temp.right());
                                if(!temp.left().isNull())
                                    {
                                        /**
                                         * Caso exista um esquerdo, ent�o
                                         * atualize temp. Esse � o caso para todos, 
                                         * fora nega��o
                                         */
                                        stack.push(temp.left());
                                        temp = temp.left();
                                    }
                                else
                                    /**
                                     * Se n�o existir esquerdo, ent�o
                                     * temp = temp->right
                                     * isso trata dos nodos un�rios, como nega��o.
                                     */
                                    temp = temp.right();
                            }
                    }
	    }
	
        return result;
    }
	
    /** Retorna falso se a pilha estiver vazia. */
    public boolean hasNext() 
    {
    	return !stack.empty();
    }

    /** Remove uma expression tree do topo da pilha. */
    public void remove() 
    {
        stack.pop();
    }
}
