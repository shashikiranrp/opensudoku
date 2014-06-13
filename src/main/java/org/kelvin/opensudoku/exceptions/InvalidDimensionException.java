package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public class InvalidDimensionException extends SudokuException
{

    private static final long serialVersionUID = 1L;
    int cause = -1;

    public InvalidDimensionException(int fCause)
    {
        cause = fCause;
    }

    @Override
    public String toString()
    {
        return super.getPrefix()
                + "InvalidDimensionException: Raised by the dimension: " + cause;
    }
}
