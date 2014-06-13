package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public class ConcreteNotNeededException extends SudokuException
{

    private static final long serialVersionUID = 1L;

    @Override
    public String toString()
    {
        return super.getPrefix()
                + " ConcreteNotNeededException: Current status of VirtualSudokuBoard doesn\'t need Concrete Analysis.";
    }
}
