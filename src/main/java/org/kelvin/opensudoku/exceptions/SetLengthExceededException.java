package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public class SetLengthExceededException extends SudokuException
{

    private static final long serialVersionUID = 1L;
    int cause = -1;

    public SetLengthExceededException(int c)
    {
        cause = c;
    }

    @Override
    public String toString()
    {
        return super.getPrefix()
                + "SetLengthExceededException: Raised by the value: " + cause;
    }
}
