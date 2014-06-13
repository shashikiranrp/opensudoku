package org.kelvin.opensudoku;

import org.kelvin.opensudoku.exceptions.InvalidBoxEntryException;
import org.kelvin.opensudoku.exceptions.InvalidDimensionException;
import org.kelvin.opensudoku.exceptions.UnIntializedParameterException;
import org.kelvin.opensudoku.fon.SudokuSetClass;

/**
 * 
 * @author Shashikiran
 */
public class VirtualSudokuBoard
{

    private int virtualBoard[][] = new int[9][9];

    public VirtualSudokuBoard()
    {
    }

    public VirtualSudokuBoard(int formalBoard[][]) throws InvalidDimensionException, InvalidBoxEntryException
    {

        //Checking for Correct Dimensions
        if (formalBoard.length != SudokuConstants.SudokuRowLimit) {
            throw new InvalidDimensionException(formalBoard.length);
        }

        for (int[] x : formalBoard) {
            if (x.length != SudokuConstants.SudokuColumnLimit) {
                throw new InvalidDimensionException(x.length);
            }
        }

        //Checking for Correct entries
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (formalBoard[i][j] < SudokuConstants.SudokuBoxMinEntry || formalBoard[i][j] > SudokuConstants.SudokuBoxMaxEntry) {
                    throw new InvalidBoxEntryException(i, j, formalBoard[i][j]);
                }
            }
        }

        this.virtualBoard = formalBoard;
    }

    public int getnoOfVoids()
    {
        int count = 0;
        for (int[] x : this.virtualBoard) {
            for (int y : x) {
                if (y == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public void update(int row, int col, int ele) throws InvalidDimensionException, InvalidBoxEntryException
    {
        if (row < SudokuConstants.SudokuMinRowLimit || row > SudokuConstants.SudokuMaxRowLimit) {
            throw new InvalidDimensionException(row);
        } else if (col < SudokuConstants.SudokuMinColumnLimit || col > SudokuConstants.SudokuMaxColumnLimit) {
            throw new InvalidDimensionException(col);
        } else if (ele < SudokuConstants.SudokuBoxMinEntry || ele > SudokuConstants.SudokuBoxMaxEntry) {
            throw new InvalidBoxEntryException(row, col, ele);
        }

        this.virtualBoard[row - 1][col - 1] = ele;
    }

    public void clear(int row, int col) throws InvalidDimensionException, InvalidBoxEntryException
    {
        this.update(row, col, 0);
    }

    public void clearRow(int row) throws InvalidDimensionException, InvalidBoxEntryException
    {
        for (int i = 1; i <= 9; i++) {
            this.update(row, i, 0);
        }
    }

    public void clearColumn(int col) throws InvalidDimensionException, InvalidBoxEntryException
    {
        for (int i = 1; i <= 9; i++) {
            this.update(i, col, 0);
        }
    }

    public void clearAll()
    {
        this.virtualBoard = new int[9][9];
    }

    public int getEntry(int row, int col) throws InvalidDimensionException
    {
        if (row < SudokuConstants.SudokuMinRowLimit || row > SudokuConstants.SudokuMaxRowLimit) {
            throw new InvalidDimensionException(row);
        } else if (col < SudokuConstants.SudokuMinColumnLimit || col > SudokuConstants.SudokuMaxColumnLimit) {
            throw new InvalidDimensionException(col);
        }
        return this.virtualBoard[row - 1][col - 1];
    }

    public int[] getRow(int row) throws InvalidDimensionException
    {
        int[] temp = new int[9];
        for (int i = 1; i <= 9; i++) {
            temp[i - 1] = this.getEntry(row, i);
        }
        return temp;
    }

    public int[] getColumn(int col) throws InvalidDimensionException
    {
        int[] temp = new int[9];
        for (int i = 1; i <= 9; i++) {
            temp[i - 1] = this.getEntry(i, col);
        }
        return temp;
    }

    public static int getSquare(int row, int col) throws InvalidDimensionException
    {
        if (row < SudokuConstants.SudokuMinRowLimit || row > SudokuConstants.SudokuMaxRowLimit) {
            throw new InvalidDimensionException(row);
        } else if (col < SudokuConstants.SudokuMinColumnLimit || col > SudokuConstants.SudokuMaxColumnLimit) {
            throw new InvalidDimensionException(col);
        }

        int square = 1;

        while (row > 3) {
            row -= 3;
            square += 3;
        }

        while (col > 3) {
            col -= 3;
            square++;
        }

        return square;
    }

    public int[] getSquareElements(int square) throws InvalidDimensionException
    {
        if (square > 9 || square < 1) {
            throw new InvalidDimensionException(square);
        }
        int s_row = ((int) (square - 1) / 3) * 3, s_col = ((square - 1) % 3) * 3;
        int ele[] = new int[10], index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ele[index++] = this.virtualBoard[i + s_row][j + s_col];
            }
        }
        return ele;
    }

    public static int getBoxNumber(int row, int col) throws InvalidDimensionException
    {
        if (row > 9 || row < 1) {
            throw new InvalidDimensionException(row);
        }

        if (col > 9 || col < 1) {
            throw new InvalidDimensionException(col);
        }

        return (((row - 1) * 9) + col);


    }

    public void printBoard()
    {
        System.out.println("State of VirtualSudokuBoard");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(this.virtualBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int[][] ReturnBoard()
    {
        return this.virtualBoard.clone();
    }

    public boolean isInRow(int ele, int row) throws InvalidDimensionException
    {
        if (row < 1 || row > 9) {
            throw new InvalidDimensionException(row);
        }
        for (int x : this.getRow(row)) {
            if (x == ele) {
                return true;
            }
        }
        return false;
    }

    public boolean isInColumn(int ele, int column) throws InvalidDimensionException
    {
        if (column < 1 || column > 9) {
            throw new InvalidDimensionException(column);
        }
        for (int x : this.getColumn(column)) {
            if (x == ele) {
                return true;
            }
        }
        return false;
    }

    public boolean isInSquare(int ele, int square) throws InvalidDimensionException
    {
        if (square < 1 || square > 9) {
            throw new InvalidDimensionException(square);
        }
        for (int x : this.getSquareElements(square)) {
            if (x == ele) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMe() throws UnIntializedParameterException, InvalidDimensionException
    {
        if (this.virtualBoard == null) {
            throw new UnIntializedParameterException("VirtualSudokuBoard.VirtualBoard");
        }
        for (int i = 1; i <= 9; i++) {
            if (SudokuCommons.returnNonZeroCount(this.getRow(i)) != SudokuCommons.returnNonZeroCount(SudokuSetClass.toSet(this.getRow(i)))) {
                return false;
            }
        }
        for (int i = 1; i <= 9; i++) {
            if (SudokuCommons.returnNonZeroCount(this.getColumn(i)) != SudokuCommons.returnNonZeroCount(SudokuSetClass.toSet(this.getColumn(i)))) {
                return false;
            }
        }
        for (int i = 1; i <= 9; i++) {
            if (SudokuCommons.returnNonZeroCount(this.getSquareElements(i)) != SudokuCommons.returnNonZeroCount(SudokuSetClass.toSet(this.getSquareElements(i)))) {
                return false;
            }
        }
        return true;
    }

    public boolean checkFull() throws InvalidDimensionException
    {
        for (int i = 1; i <= 9; i++) {
            if (this.isInRow(0, i)) {
                return false;
            }
        }
        return true;
    }
}