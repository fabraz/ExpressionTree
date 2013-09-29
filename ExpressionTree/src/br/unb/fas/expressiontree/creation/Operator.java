package br.unb.fas.expressiontree.creation;

/**
 * @class Operator
 *
 * @brief Define a classe base para expressões não-terminais operadores.
 */
public abstract class Operator extends Symbol
{
    /** Precedência dos operadores '+' e '-'. */
    final static int addSubPrecedence = 1;
    
    /** Precedência dos operadores '*' e '/'. */
    final static int mulDivPrecedence = 2;
    
    /** Ctor */
    Operator(Symbol left,
             Symbol right,
             int precedence)
    {
        super(left, right, precedence);
    }
}
