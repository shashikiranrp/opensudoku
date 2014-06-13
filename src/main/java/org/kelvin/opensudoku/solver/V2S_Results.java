package org.kelvin.opensudoku.solver;

import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.concrete.ConcreteResults;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.SetLengthExceededException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.FonResults;

/**
 * 
 * @author Shashikiran
 */
public class V2S_Results
{

    private ConcreteResults v2sCr;

    public V2S_Results(ConcreteResults cr)
    {
        this.v2sCr = cr;
    }

    public V2S_Results()
    {
        this.v2sCr = null;
    }

    public boolean checkAll() throws UnIntializedParameterException, SetLengthExceededException, InvalidDimensionException
    {
        if (this.v2sCr == null) {
            throw new UnIntializedParameterException("\'V2S_Results.V2S_CR\'");
        }
        for (int i = 0; i < this.v2sCr.getNoOfSolutions(); i++) {
            if (!this.v2sCr.ReturnSolutionAt(i).checkMe() || !this.v2sCr.ReturnSolutionAt(i).checkFull()) {
                return false;
            }
        }
        return true;
    }

    public ConcreteResults returnCRReference()
    {
        return this.v2sCr;
    }

    public FonResults returnFonReference()
    {
        return this.v2sCr.ReturnFonReference();
    }

    public int totalNoOfSolutions()
    {
        return this.v2sCr.getNoOfSolutions();
    }

    public VirtualSudokuBoard testAndReturnSolutionAt(int index) throws SetLengthExceededException, UnIntializedParameterException, InvalidDimensionException
    {
        if (this.v2sCr.ReturnSolutionAt(index).checkMe() && this.v2sCr.ReturnSolutionAt(index).checkFull()) {
            return this.v2sCr.ReturnSolutionAt(index);
        }
        return null;
    }

    public void printSolutionAt(int index) throws SetLengthExceededException
    {
        this.v2sCr.ReturnSolutionAt(index).printBoard();
    }

    public VirtualSudokuBoard returnSolutionAt(int index) throws SetLengthExceededException
    {
        return this.v2sCr.ReturnSolutionAt(index);
    }

    public boolean isConcreteApplied()
    {
        return !this.v2sCr.ReturnFonReference().concreteFlag;
    }
}
