package br.unb.fas.expressiontree.traversal;

import java.util.Iterator;
import java.util.Stack;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class InOrderIterator
 * 
 * @brief Iterage por uma @a Tree usando a forma de percorrer in-order. 

 */
public class InOrderIterator implements Iterator<ExpressionTree>
{
    /** Pilha de trees. */
    private Stack <ExpressionTree> stack = new Stack<ExpressionTree>();
    
    /** Ctor */
    public InOrderIterator(ExpressionTree tree)
    {
        if(!tree.isNull()) 
            {
                stack.push(tree);
		
                while(!stack.peek().left().isNull()) 
                    stack.push(stack.peek().left());
            }
    }
	
    /** Move o iterator para a próxima expression tree na pilha. */
    public ExpressionTree next() 
    {
        ExpressionTree result = stack.peek();
		
        if (!stack.isEmpty())
	    {
                /** Caso tenhamos nodos maiores que o proprio */
                if (!stack.peek().right().isNull())
                    {
                        /**
                         * Empilha o nodo filho direito e desempilha o pai 
                         */
                        stack.push(stack.pop().right());

                        /** 
                         * Mantem o empilhamento ate chegar ao filho mais a esquerda.
                         */
                        while(!stack.peek().left().isNull())
                            stack.push(stack.peek().left());
                    }
                else
                    stack.pop();
	    }
	
        return result;
    }
	
    /** Retorna falso se a pilha estiver vazia. */
    public boolean hasNext() 
    {
        return !stack.empty();
    }

    /** Remove uma arvore de expressao do topo da pilha. */
    public void remove() 
    {
        stack.pop();
    }
}
