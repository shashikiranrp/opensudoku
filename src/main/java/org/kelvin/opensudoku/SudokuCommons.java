package org.kelvin.opensudoku;

/**
 * 
 * @author Shashikiran
 */
public final class SudokuCommons
{

    public static int returnNonZeroCount(int[] a)
    {
        int count = 0;
        for (int x : a) {
            if (x != 0) {
                count++;
            }
        }
        return count;
    }
}
