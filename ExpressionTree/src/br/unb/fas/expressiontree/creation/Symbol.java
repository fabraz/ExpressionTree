package br.unb.fas.expressiontree.creation;

import br.unb.fas.expressiontree.structure.ComponentNode;


/**
 * @class Symbol
 *
 * @brief Classe base para as várias subclasses de parse tree.
 */
abstract class Symbol
{
    /** 
     * As seguintes constantes definem o níveis de precedência para os vários operadores e operandos
     */

    /** Precedência padrão. */
    protected int precedence = 0;

    /** Left symbol. */
    protected Symbol left;

    /** Right symbol. */
    protected Symbol right;

    /** Ctor */
    public Symbol(Symbol left,
                  Symbol right,
                  int precedence)
    {
        this.precedence = precedence;
        this.left = left;
        this.right = right;
    }

    /** 
     * Método par retornar o nível de precedência (valores maiores significam 
     * precedência mais elevada
     */
    public int precedence()
    {
        return precedence;
    }

    /** Método abstrato para adicionar precedência. */
    public abstract int addPrecedence(int accumulatedPrecedence);

    /** Método abstrato para construir um @a ComponentNode. */
    abstract ComponentNode build();
}

