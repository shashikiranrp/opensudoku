package org.kelvin.opensudoku.exceptions;

public class InvalidBoxEntryException extends SudokuException
{

    private static final long serialVersionUID = 1L;
    private int row, col, cause;

    public InvalidBoxEntryException(int fRow, int fCol, int fCause)
    {
        super();
        row = fRow;
        col = fCol;
        cause = fCause;
    }

    @Override
    public String toString()
    {
        return super.getPrefix()
                + "InvalidBoxEntryException: At row " + this.row + ", column: " + this.col + ",raised by the input: " + this.cause;
    }
}
