package org.kelvin.opensudoku.fon;

import org.kelvin.opensudoku.SudokuConstants;
import org.kelvin.opensudoku.VirtualSudokuBoard;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;

/**
 * 
 * @author Shashikiran
 */
public class VoidClass
{

    private int row = -1, column = -1, squareNo = -1, boxNo = -1, posList[] = null;
    private boolean FonsFlag = false;

    public VoidClass()
    {
        this.row = this.column = this.boxNo = this.squareNo = 0;
        this.posList = null;
        this.FonsFlag = false;
    }

    public VoidClass(int row, int col) throws InvalidDimensionException
    {
        this.row = row;
        this.column = col;
        this.squareNo = VirtualSudokuBoard.getSquare(row, col);
        this.boxNo = VirtualSudokuBoard.getBoxNumber(row, col);
        this.posList = null;
        this.FonsFlag = false;
    }

    public int[] fonCP_AI(VirtualSudokuBoard vb) throws InvalidDimensionException
    {
        int a[] = SudokuSetClass.minus(SudokuConstants.SudokuUniversalSet, vb.getSquareElements(squareNo));
        int b[] = SudokuSetClass.minus(SudokuConstants.SudokuUniversalSet, vb.getColumn(column));
        int c[] = SudokuSetClass.minus(SudokuConstants.SudokuUniversalSet, vb.getRow(row));
        return SudokuSetClass.intersection(a, SudokuSetClass.intersection(b, c));
    }

    public int getMyBox()
    {
        return this.boxNo;
    }

    public int getMySquare()
    {
        return this.squareNo;
    }

    public void setPosList(int[] v)
    {
        this.posList = v;
    }

    public int[] getPosList()
    {
        return this.posList;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.column;
    }

    public boolean isNotProcessed()
    {
        return !(this.FonsFlag);
    }

    public void setFonsFlag(boolean flag)
    {
        this.FonsFlag = flag;
    }

    public int getPossibilityCount()
    {
        int count = 0;
        for (int x : this.posList) {
            if (x != 0) {
                count++;
            }
        }
        return count;
    }

    public int[] getOnlyPossList()
    {
        int[] temp = new int[this.getPossibilityCount()];
        int i = 0;
        for (int x : this.posList) {
            if (x != 0) {
                temp[i++] = x;
            }
        }
        return temp;
    }

    public void printMe()
    {
        for (int x : this.posList) {
            System.out.print(x + "\t");
        }
        System.out.println();
    }

    public int getNthPoss(int n)
    {
        return this.getOnlyPossList()[n];
    }
}
