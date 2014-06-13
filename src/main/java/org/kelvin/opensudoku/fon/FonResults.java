package org.kelvin.opensudoku.fon;

import java.util.Vector;
import org.kelvin.opensudoku.VirtualSudokuBoard;

/**
 * 
 * @author Shashikiran
 */
public class FonResults
{

    public static void displayFonResults(FonResults FonResult)
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Fon Statistics...\n******************");
        System.out.println("No. Of Voids before Fon: " + FonResult.noOfVoidsBeforeFon);
        System.out.println("No. Of Voids after Fon: " + FonResult.noOfVoidsAfterFon);
        System.out.println("Status of Concrete Flag: " + FonResult.concreteFlag);
        System.out.println("Board Status after Fon: \n******************");
        FonResult.fonSolutionBoard.printBoard();
        System.out.println("Fon Completed in " + FonResult.fonSteps + " passes.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
    public VirtualSudokuBoard fonSolutionBoard;
    public int noOfVoidsBeforeFon, noOfVoidsAfterFon;
    public boolean concreteFlag;
    public Vector<?> fonAfterVoids;
    public int fonSteps;

    public FonResults()
    {
        this.concreteFlag = false;
        this.fonAfterVoids = null;
        this.fonSolutionBoard = null;
        this.fonSteps = 0;
        this.noOfVoidsAfterFon = 0;
        this.noOfVoidsBeforeFon = 0;
    }
}
