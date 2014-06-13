package org.kelvin.opensudoku.concrete;

import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;

/**
 * 
 * @author Shashikiran
 */
public class ConcreteSupportClass
{

    public static VirtualSudokuBoard returnVsbObject(VirtualSudokuBoard vsb) throws InvalidDimensionException, InvalidBoxEntryException
    {
        return new VirtualSudokuBoard(vsb.ReturnBoard());
    }

    public static boolean checkForPlacement(VirtualSudokuBoard vsb, int row, int col, int ele) throws InvalidDimensionException
    {
        return (!vsb.isInRow(ele, row)) && (!vsb.isInColumn(ele, col)) && (!vsb.isInSquare(ele, VirtualSudokuBoard.getSquare(row, col)));
    }
}
