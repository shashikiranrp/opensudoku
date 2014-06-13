package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public class NoMorePossibilitiesException extends SudokuException
{

    private static final long serialVersionUID = 1L;
    private int squareNo = -1;

    public NoMorePossibilitiesException(int sq)
    {
        this.squareNo = sq;
    }

    @Override
    public String toString()
    {
        return super.getPrefix()
                + " NoMorePossibilitiesException: Possibility list is empty for square " + squareNo + ".";
    }
}
