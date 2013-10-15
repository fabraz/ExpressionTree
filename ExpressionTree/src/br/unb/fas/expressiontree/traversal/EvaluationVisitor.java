package br.unb.fas.expressiontree.traversal;

import java.util.Stack;

import br.unb.fas.expressiontree.structure.*;

/**
 * @class EvaluationVisitor
 *
 * @brief Esta classe exerce o papel do visitor para avalia��o dos nodos em 
 * 		uma expression tre que � usada para iteragir no modo post-order (n�o 
 * 		vai funcionar em outros modos de itera��o). Essa classe exerce o papel
 * 		do "ConcreteVisitor" no padr�o visitor.
 */
public class EvaluationVisitor implements Visitor
{
    /** 
     * Pilha contendo o conjunto total de expression tree que ser� visitada.
     */
    private Stack<Integer> stack =
        new Stack<Integer>();

    /** Ctor. */
    public EvaluationVisitor()
    {
    }

    /** Visita um @a LeafNode. */
    public void visit(LeafNode node) 
    {
        stack.push(node.item());
    }

    /** Visita um  CompositeSubtractNode. */
    public void visit(CompositeNegateNode node) 
    {
        if(stack.size() >= 1)
            stack.push(-stack.pop());
    }

    /** Visita um @a CompositeAddNode. */
    public void visit(CompositeAddNode node)
    {
        if(stack.size() >= 2)
            stack.push(stack.pop() + stack.pop());
    }

    /** Visita um @a CompositeSubtractNode. */
    public void visit(CompositeSubtractNode node)
    {
        if(stack.size() >= 2)
            {
                int rhs =stack.pop();
                stack.push(stack.pop() -rhs);
            }
    }

    /** Visita um @a CompositeDivideNode. */
    public void visit(CompositeDivideNode node) 
    {
        if(stack.size() >= 2 && stack.peek() != null)
            {
                int rhs = stack.pop();
                stack.push(stack.pop() / rhs);
            }
        else
            {
        	
        		System.out.println("EvaluationVisitor \n\n**: Divis�o por zero n�o � permitida.");
        		System.out.println("EvaluationVisitor \n\n** Reiniciando evaluation visitor.\n\n");
                reset();
            }
    }

    /** Visita um @a CompositeMultiplyNode. */
    public void visit(CompositeMultiplyNode node)
    {
        if (stack.size () >= 2)
            stack.push (stack.pop () * stack.pop ());
    }

    /** Informa o resultado da avalia��o. */
    public int total()
    {
        if(!stack.isEmpty())
            return stack.peek();
        else
            return 0;
    }

    /** Retorna o avaliador para a sua condi��o inicial de uso. */
    public void reset()
    {
        stack.clear();

    }
}
