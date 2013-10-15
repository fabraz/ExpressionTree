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
                 * O código comentado não funciona em um nodo operador unário sem 
                 * nodos a esquerda, mas a direita - ou pelo menos, existe algum 
                 * profundidade que não seja aprofundada.
                 */
                while(!tree.isNull())
                    {
                        if(!tree.right().isNull())
                            stack.push(tree.right());
                        if(!tree.left().isNull())
                            {
                                /** 
                                 * Se existir um esquerdo, atualize o atual, 
                                 * esse é o caso de todos que não sejam negações.
                                 */
                                stack.push(tree.left());
                                tree = tree.left();
                            }
                        else 
                            {
                                /** 
                                 * Se não existir esquero, então 
                                 * current = current.right_ isso trata dos nos unarios, 
                                 * como as negações.
                                 */
                                tree = tree.right();
                            }
                    }
            }
    }
	
    /** Avança a próxima expression tree na pilha. */
    public ExpressionTree next() 
    {
        ExpressionTree result = stack.peek();
        if (!stack.isEmpty())
	    {
                /**
                 * É necessário retirar o nó da pulha antes de 
                 * empilhar o filho, ou então revisitaremos esse nó no futuro
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
                                         * Caso exista um esquerdo, então
                                         * atualize temp. Esse é o caso para todos, 
                                         * fora negação
                                         */
                                        stack.push(temp.left());
                                        temp = temp.left();
                                    }
                                else
                                    /**
                                     * Se não existir esquerdo, então
                                     * temp = temp->right
                                     * isso trata dos nodos unários, como negação.
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
