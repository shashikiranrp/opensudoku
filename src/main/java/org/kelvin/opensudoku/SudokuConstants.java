package org.kelvin.opensudoku;

/**
 * 
 * @author Shashikiran
 */
public final class SudokuConstants
{

    public static final int SudokuUniversalSet[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int SudokuRowLimit = 9;
    public static final int SudokuColumnLimit = 9;
    public static final int SudokuSquareLimit = 9;
    public static final int SudokuBoxLimit = 81;
    public static final int SudokuSetLimit = 9;
    private static int SudokuMaxSolutionsLimit = 50;
    private static final int SudokuAtMostMaxLimit = 10000;
    public static final int InvalidDimensionException = 100;
    public static final int InvalidBoxEntryException = 101;
    public static final int ConcreteNotNeededException = 102;
    public static final int SudokuException = 103;
    public static final int SetLengthExceededException = 104;
    public static final int UnIntializedParameterException = 105;
    public static final int NoMorePossibilitiesException = 106;
    public static final int EveryThingIsOk = 0;
    public static final int SudokuBoxMaxEntry = 9;
    public static final int SudokuBoxMinEntry = 0;
    public static final int SudokuMinRowLimit = 1;
    public static final int SudokuMaxRowLimit = 9;
    public static final int SudokuMinColumnLimit = 1;
    public static final int SudokuMaxColumnLimit = 9;
    public static final int SudokuMinSquareLimit = 1;
    public static final int SudokuMaxSquareLimit = 9;

    public static boolean setSudokuMaxSolutionLimit(int limit)
    {
        if (limit > SudokuConstants.SudokuAtMostMaxLimit || limit <= 0) {
            return false;
        } else {
            SudokuConstants.SudokuMaxSolutionsLimit = limit;
            return true;
        }
    }

    public static int getSudokuMaxSolutionLimit()
    {
        return SudokuConstants.SudokuMaxSolutionsLimit;
    }
}
