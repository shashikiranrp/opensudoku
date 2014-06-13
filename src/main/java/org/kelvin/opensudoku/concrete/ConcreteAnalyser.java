package org.kelvin.opensudoku.concrete;

import java.util.Vector;
import org.kelvin.opensudoku.SudokuConstants;
import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.VoidClass;

//Solving the puzzle using LCF Backtracking...
/**
 * 
 * @author Shashikiran
 */
public class ConcreteAnalyser
{

    private Vector<?> cvv;
    private int[] indexVector;
    private int[] lenVector;
    private VirtualSudokuBoard vsb;
    private int currentVoid;
    private boolean stopFlag;
    private Vector<VirtualSudokuBoard> solutionVector;

    public ConcreteAnalyser()
    {
        this.cvv = null;
        this.indexVector = null;
        this.lenVector = null;
        this.vsb = null;
        this.currentVoid = 0;
        this.stopFlag = false;
    }

    /**
     * Constructor
     * @param cvv
     * @param v
     * @throws InvalidDimensionException
     * @throws InvalidBoxEntryException
     */
    public ConcreteAnalyser(Vector<?> cvv, VirtualSudokuBoard v) throws InvalidDimensionException, InvalidBoxEntryException
    {
        int i = 0;
        this.cvv = (Vector<?>) cvv.clone();
        this.vsb = ConcreteSupportClass.returnVsbObject(v);
        this.indexVector = new int[this.cvv.size()];
        this.lenVector = new int[this.cvv.size()];
        for (Object ob : this.cvv) {
            this.lenVector[i++] = ((VoidClass) ob).getPossibilityCount();
        }
        this.currentVoid = 0;
        this.stopFlag = false;
    }

    /**
     *
     * @return Returns the solved board states after concrete analysis
     * @throws UnIntializedParameterException
     * @throws InvalidDimensionException
     * @throws InvalidBoxEntryException
     */
    public Vector<VirtualSudokuBoard> doConcreteAnalysis() throws UnIntializedParameterException, InvalidDimensionException, InvalidBoxEntryException
    {
        if (this.cvv == null) {
            throw new UnIntializedParameterException("ConcreteAnalyser.CVV");
        }
        this.solutionVector = new Vector<>();
        while (!this.stopFlag) {
            VoidClass ob = (VoidClass) this.cvv.get(currentVoid);
            if (ConcreteSupportClass.checkForPlacement(this.vsb, ob.getRow(), ob.getCol(), ob.getNthPoss(this.indexVector[this.currentVoid]))) {
                this.setVoid();

                //Continue if OK...
                this.continueToNextLevel();
            } else {
                //Else BackTrack
                this.lCFBackTrak();
            }
        }
        return this.solutionVector;
    }

    private void setVoid() throws InvalidDimensionException, InvalidBoxEntryException
    {
        VoidClass ob = (VoidClass) this.cvv.get(currentVoid);

        //Updating the CurrentVoid with a allowable possible value
        this.vsb.update(ob.getRow(), ob.getCol(), ob.getNthPoss(this.indexVector[this.currentVoid]));
    }

    //Lowest Cardinality First BackTracking
    /**
     * Modified Backtracking function
     * @throws InvalidDimensionException
     * @throws InvalidBoxEntryException
     */
    private void lCFBackTrak() throws InvalidDimensionException, InvalidBoxEntryException
    {
        boolean continueFlag;
        do {
            //Unsetting the CurrentVoid Place...
            this.unsetVoid();

            //Updating the Inter-Related List...
            continueFlag =
                    ((this.indexVector[this.currentVoid] = ((this.indexVector[this.currentVoid] + 1) % this.lenVector[this.currentVoid]))) == 0;

        } while (continueFlag && (--this.currentVoid) >= 0);

        this.stopFlag = (this.currentVoid == -1);
    }

    private void unsetVoid() throws InvalidDimensionException, InvalidBoxEntryException
    {
        VoidClass ob = (VoidClass) this.cvv.get(currentVoid);

        //Updating the CurrentVoid with '0'
        this.vsb.update(ob.getRow(), ob.getCol(), 0);
    }

    private void continueToNextLevel() throws InvalidDimensionException, InvalidBoxEntryException
    {
        //Checking whether solution state is obtained or not...
        if (this.currentVoid == (this.cvv.size() - 1)) {
            //Checking whether current no of solutions exceeds the maximum solution limit...
            if (this.solutionVector.size() < SudokuConstants.getSudokuMaxSolutionLimit()) {
                //Adding the solution to solution vector as VSB object...
                this.solutionVector.addElement(this.returnAsBoard(this.vsb.ReturnBoard()));
                //Backtracking to get next possible solutions...
                this.lCFBackTrak();
            } else //Setting stopflag after current no of solutions exceeds the maximum solution limit...
            {
                this.stopFlag = true;
            }
        } else //Moving down in the State Space Tree if current state is not representing the solution state...
        {
            this.currentVoid++;
        }
    }

    private VirtualSudokuBoard returnAsBoard(int[][] v) throws InvalidDimensionException, InvalidBoxEntryException
    {
        //Creating new 2D Array and also returning the corresponding VSB
        int[][] temp = new int[9][9];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                temp[i - 1][j - 1] = v[i - 1][j - 1];
            }
        }
        return new VirtualSudokuBoard(temp);
    }
}
