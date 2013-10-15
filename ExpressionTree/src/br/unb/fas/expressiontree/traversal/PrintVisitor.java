package  br.unb.fas.expressiontree.traversal;

import br.unb.fas.expressiontree.structure.*;

/**
 * @class PrintVisitor
 *
 * @brief Essa classe serve como um visitor que imprime o conteúdo de
 * 		cada nodo na expression tree. Esta classe exerce o papel do 
 * 		"ConcreteVisitor" no padrão Visitor.
 */
public class PrintVisitor implements Visitor
{
    /** Ctor */
    public PrintVisitor()
    {    
    }

    /** Visita um @a LeafNode and prints it contents. */
    public void visit(LeafNode node) 
    {
    	System.out.println(node.item() + " " );
    }

    /** Visita um @a CompositeNegateNode and prints its contents. */
    public void visit(CompositeNegateNode node) 
    {
    	System.out.println("-");
    }

    /** Visita um @a CompositeAddNode and prints its contents. */
    public void visit(CompositeAddNode node)
    {
    	System.out.println("+ ");
    }

    /** Visita um @a CompositeSubtractNode and prints its contents. */
    public void visit(CompositeSubtractNode node)
    {
    	System.out.println("- ");
    }

    /** Visita um @a CompositeDivideNode and prints its contents. */
    public void visit(CompositeDivideNode node)
    {
    	System.out.println("/ ");
    }

    /** Visita um @a CompositeMultiplyNode and print its contents. */
    public void visit(CompositeMultiplyNode node) 
    {
    	System.out.println("* ");
    }
}
