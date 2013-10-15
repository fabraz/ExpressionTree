package  br.unb.fas.expressiontree.traversal;

import br.unb.fas.expressiontree.structure.*;

/**
 * @class Visitor
 * 
 * @brief Classe abstrata base para todos os visitors de todas as classes que derivam
 * 		de @a ComponentNode.  Essa classe exerce o papel de "Visitor" no padrão Visitor.
 */
public interface Visitor
{
    /** Visita um @a LeafNode. */
    void visit(LeafNode node);

    /** Visita um @a CompositeNegateNode. */
    void visit(CompositeNegateNode node);

    /** Visita um @a CompositeAddNode. */
    void visit(CompositeAddNode node);

    /** Visita um @a CompositeSubtractNode. */
    void visit(CompositeSubtractNode node);

    /** Visita um @a CompositeDivideNode. */
    void visit(CompositeDivideNode node);

    /** Visita um @a CompositeMultiplyNode. */
    void visit(CompositeMultiplyNode node);
}
