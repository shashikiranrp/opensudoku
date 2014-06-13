package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public abstract class SudokuException extends RuntimeException
{

    private static final long serialVersionUID = 1L;
    private static final String prefix = "SudokuException: ";

    protected String getPrefix()
    {
        return prefix;
    }
}
