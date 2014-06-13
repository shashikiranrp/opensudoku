package org.kelvin.opensudoku.concrete;

import java.util.Vector;
import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.SetLengthExceededException;
import org.kelvin.opensudoku.fon.FonResults;

/**
 * 
 * @author Shashikiran
 */
public class ConcreteResults
{

    private Vector<?> solutionVector;
    private int noOfSolutions;
    private FonResults FR;

    public ConcreteResults()
    {
        this.FR = null;
        this.solutionVector = null;
        this.noOfSolutions = 0;
    }

    public ConcreteResults(Vector<?> v, FonResults fr)
    {
        this.FR = fr;
        this.solutionVector = v;
        this.noOfSolutions = v.size();
    }

    public int getNoOfSolutions()
    {
        return this.noOfSolutions;
    }

    public VirtualSudokuBoard ReturnSolutionAt(int index) throws SetLengthExceededException
    {
        if (index < 1 || index > this.noOfSolutions) {
            throw new SetLengthExceededException(index);
        }
        return (VirtualSudokuBoard) this.solutionVector.get(index - 1);
    }

    public FonResults ReturnFonReference()
    {
        return this.FR;
    }
}
