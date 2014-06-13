package org.kelvin.opensudoku.fon;

import java.util.Vector;
import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;

/**
 * 
 * @author Shashikiran
 */
//The Fon-First One Normalize Class
public class Fon
{

    //Sudoku Board & void vector
    private VirtualSudokuBoard fonsBoard = null;
    private Vector<VoidClass> fonsVoidVector = null;

    public Fon()
    {
        this.fonsBoard = null;
    }

    public Fon(VirtualSudokuBoard vsb)
    {
        this.fonsBoard = vsb;
    }

    private void fonAnalysis() throws UnIntializedParameterException, InvalidDimensionException
    {
        VoidClass vc = null;
        if (this.fonsBoard == null) {
            throw new UnIntializedParameterException("\'Fon.FonsBoard\'");
        }
        this.fonsVoidVector = new Vector<VoidClass>();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (this.fonsBoard.getEntry(i, j) == 0) {
                    vc = new VoidClass(i, j);
                    vc.setPosList(vc.fonCP_AI(this.fonsBoard));
                    this.fonsVoidVector.add(vc);
                }
            }
        }
    }

    private boolean isOne(int[] x)
    {
        int count = 0;
        for (int c : x) {
            if (c > 0) {
                count++;
            }
        }
        return count == 1;
    }

    private VoidClass fonGetNextVoid()
    {
        for (Object vc : this.fonsVoidVector) {
            if (isOne(((VoidClass) vc).getPosList()) && ((VoidClass) vc).isNotProcessed()) {
                return ((VoidClass) vc);
            }
        }
        return null;
    }

    private int getOne(int[] x)
    {
        for (int c : x) {
            if (c > 0) {
                return c;
            }
        }
        return 0;
    }

    private void fonSetNextVoid(VoidClass vc) throws InvalidDimensionException, InvalidBoxEntryException
    {
        vc.setFonsFlag(true);
        this.fonsBoard.update(vc.getRow(), vc.getCol(), getOne(vc.getPosList()));
    }

    private int fonCurrentNormalize() throws InvalidDimensionException, InvalidBoxEntryException
    {
        int count = 0;
        VoidClass vc;
        while ((vc = this.fonGetNextVoid()) != null) {
            this.fonSetNextVoid(vc);
            count++;
        }
        return count;
    }

    public FonResults doFon() throws UnIntializedParameterException, InvalidDimensionException, InvalidBoxEntryException
    {
        FonResults FonResult = new FonResults();
        int count = 0;

        this.fonAnalysis();

        FonResult.noOfVoidsBeforeFon = this.fonsBoard.getnoOfVoids();
        while (this.fonCurrentNormalize() != 0) {
            this.fonAnalysis();
            count++;
        }
        FonResult.fonSolutionBoard = this.fonsBoard;
        FonResult.noOfVoidsAfterFon = this.fonsBoard.getnoOfVoids();
        if (FonResult.noOfVoidsAfterFon > 0) {
            this.fonAnalysis();
            FonResult.concreteFlag = true;
            FonResult.fonAfterVoids = this.fonsVoidVector;
        }
        FonResult.fonSteps = count;
        return FonResult;
    }
}