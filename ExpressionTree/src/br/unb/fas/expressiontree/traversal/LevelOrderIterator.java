package br.unb.fas.expressiontree.traversal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import br.unb.fas.expressiontree.structure.ExpressionTree;

/**
 * @class LevelOrderIterator
 *
 * @brief Iterage por uma @a Tree usando a forma de percorrer level-order. 
 */
public class LevelOrderIterator implements Iterator<ExpressionTree>
{
    /** Fila de expression trees. */
    private Queue <ExpressionTree> queue =
        new LinkedList<ExpressionTree>();
    
    /** Ctor */
    public LevelOrderIterator(ExpressionTree tree)
    {
        if(!tree.isNull()) 
            queue.add(tree);
    }
	
    /** Move o iterator para a próxima expression tree na fila. */
    public ExpressionTree next() 
    {
        /** Armazena o elemento frontal na fila. */
        ExpressionTree result = queue.peek();
		
        if (!queue.isEmpty())
            {
                /** 
                 * Precisamos retirar o nodo da pilha antes de empilhar o filho,
                 * caso contrário esse nodo sera revisitado posteriormente
                 */
                ExpressionTree temp = queue.remove();

                /**
                 * Observe a ordem: primeiro direita, depois esquerda. Uma vez que 
                 * configura um LIFO (last input first output), isso resulta no
                 * filho da esquerda sendo o primeiro avaliado, o que se encaixa na
                 * estrategia pre-order.
                 */
                if (!temp.right().isNull())
                    queue.add (temp.right());
                if (!temp.left().isNull())
                    queue.add (temp.left());
            }
		
        return result;
    }
	
    /** Verifica se a fila esta vazia. */
    public boolean hasNext() 
    {
        return !queue.isEmpty();
    }

    /** Remove uma expression tree da frente da fila. */
    public void remove() 
    {
        queue.remove();
    }
}
