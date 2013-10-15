package br.unb.fas.expressiontree.traversal;

import java.util.Iterator;
import java.util.Stack;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class PreOrderIterator
 * 
 * @brief Iterage por uma @a Tree usando a forma de percorrer pre-order. 
 */
public class PreOrderIterator implements Iterator<ExpressionTree>
{
    /** Pilha de expression trees. */
    private Stack <ExpressionTree> stack =
        new Stack<ExpressionTree>();
	
    /** Ctor */
    public PreOrderIterator(ExpressionTree tree)
        {
            if (!tree.isNull()) 
                stack.push(tree);
        }

    /** Avança a próxima expression tree na pilha. */
    public ExpressionTree next() 
    {
        ExpressionTree result = stack.peek();
			
        if (!stack.isEmpty())
            {
                /**
                 * Precisamos retirar o nodo da pilha antes
                 * de empilhar o filho, ou senão revisitaremos
                 * esse nó posteriormente.
                 */
                ExpressionTree temp = stack.pop();
	
                /** 
                 * Observer a ordem aqui: primeiro direito, depois esquerdo. 
                 * Uma vez que se trata de um LIFO, isso resulta em um filho 
                 * esquerdo sendo o primeiro avaliado, o que se encaixa
                 * na estratégia Pre-order.
                 */
                if (!temp.right().isNull())
                    stack.push(temp.right());
                if (!temp.left().isNull())
                    stack.push(temp.left());
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
