/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kelvin.opensudoku.solver;

import org.kelvin.opensudoku.SudokuConstants;
import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.ConcreteNotNeededException;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.NoMorePossibilitiesException;
import org.kelvin.opensudoku.exceptions.SudokuException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.Fon;
import org.kelvin.opensudoku.fon.FonResults;

/**
 * 
 * @author Shashikiran
 */
public class V2S_Sudoku
{

    private V2S_Results results;
    private int exceptionCode;
    private SudokuException exception;

    public V2S_Sudoku()
    {
        this.results = null;
        this.exceptionCode = SudokuConstants.EveryThingIsOk;
        this.exception = null;
    }

    public void solve(VirtualSudokuBoard vsb)
    {
        if (vsb == null) {
            throw new IllegalArgumentException("Null Sudoku board.");
        }
        try {
            this.results = V2S_Solver.returnResults(vsb);
        } catch (UnIntializedParameterException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.UnIntializedParameterException;
        } catch (ConcreteNotNeededException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.ConcreteNotNeededException;
        } catch (NoMorePossibilitiesException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.NoMorePossibilitiesException;
        } catch (InvalidDimensionException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.InvalidDimensionException;
        } catch (InvalidBoxEntryException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.InvalidBoxEntryException;
        }
    }

    public V2S_Results returnResult()
    {
        return this.results;
    }

    public boolean isConcreteApplied()
    {
        return this.exceptionCode != SudokuConstants.ConcreteNotNeededException;
    }

    public int getExceptionCode()
    {
        return this.exceptionCode;
    }

    public SudokuException getExceptionRef()
    {
        return this.exception;
    }

    public boolean getOkExceptionStatus()
    {
        return this.exceptionCode == SudokuConstants.EveryThingIsOk;
    }

    public FonResults getResultsAsFonResults(VirtualSudokuBoard vsb)
    {
        try {
            return new Fon(vsb).doFon();
        } catch (UnIntializedParameterException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.UnIntializedParameterException;
        } catch (InvalidDimensionException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.InvalidDimensionException;
        } catch (InvalidBoxEntryException ex) {
            this.exception = ex;
            this.exceptionCode = SudokuConstants.InvalidBoxEntryException;
        }
        return null;
    }
}
