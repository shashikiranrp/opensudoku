package org.kelvin.opensudoku.exceptions;

/**
 * 
 * @author Shashikiran
 */
public class UnIntializedParameterException extends SudokuException
{

    private static final long serialVersionUID = 1L;
    private String paramName = null;

    public UnIntializedParameterException(String cause)
    {
        paramName = cause;
    }

    @Override
    public String toString()
    {
        return super.getPrefix()
                + "UnIntializedParameterException: One(Some) of the Parameter(s) is(are) required to be intialized-(" + this.paramName + ").";
    }
}
