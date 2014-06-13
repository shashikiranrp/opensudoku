package org.kelvin.opensudoku.fon;

import org.kelvin.opensudoku.exceptions.SetLengthExceededException;

/**
 * 
 * @author Shashikiran
 */
public class SudokuSetClass
{

    private int[] SSet = new int[10];
    private static boolean[] hashArray = new boolean[10];
    private int setCount = 0;

    public int getSetCount()
    {
        return this.setCount;
    }

    public int getElement(int pos) throws SetLengthExceededException
    {
        if (pos > this.setCount || pos < 0) {
            throw new SetLengthExceededException(pos);
        }
        return this.SSet[pos];
    }

    public void setElement(int pos, int ele) throws SetLengthExceededException
    {
        if (pos > this.setCount || pos < 0) {
            throw new SetLengthExceededException(pos);
        }
        this.SSet[pos - 1] = ele;
        this.SSet = SudokuSetClass.toSet(this.SSet);
        this.setCount = SudokuSetClass.getLen(this.SSet);
    }

    public void addElement(int ele) throws SetLengthExceededException
    {
        if (this.setCount == 9) {
            throw new SetLengthExceededException(10);
        }
        this.SSet[++this.setCount] = ele;
        this.SSet = SudokuSetClass.toSet(this.SSet);
        this.setCount = SudokuSetClass.getLen(this.SSet);
    }

    public static int[] toSet(int[] tempArray)
    {
        int[] temp = new int[10];
        int j = 0;
        SudokuSetClass.hashArray = new boolean[10];
        for (int x : tempArray) {
            SudokuSetClass.hashArray[x] = true;
        }
        for (int i = 0; i <= 9; i++) {
            if (SudokuSetClass.hashArray[i]) {
                temp[j++] = i;
            }
        }
        return temp;
    }

    public static int getLen(int[] a)
    {
        int len = 0;
        while (len < 10 && a[len++] != 0)
        	;
        return len;
    }

    public static int[] union(int a[], int b[])
    {
        SudokuSetClass.hashArray = new boolean[10];
        int[] temp = new int[10];
        int j = 0;
        for (int x : a) {
            hashArray[x] = true;
        }
        for (int x : b) {
            hashArray[x] = true;
        }
        for (int i = 0; i < 10; i++) {
            if (hashArray[i]) {
                temp[j++] = i;
            }
        }
        return temp;
    }

    public static int[] intersection(int a[], int b[])
    {
        SudokuSetClass.hashArray = new boolean[10];
        int[] temp = new int[10];
        int j = 0;
        for (int x : a) {
            if (x > 0) {
                hashArray[x] = true;
            }
        }
        for (int x : b) {
            if (x > 0 && hashArray[x]) {
                temp[j++] = x;
            }
        }
        return temp;
    }

    public static int[] minus(int a[], int b[])
    {
        SudokuSetClass.hashArray = new boolean[10];
        int[] temp = new int[10];
        int j = 0;
        for (int x : a) {
            hashArray[x] = true;
        }

        for (int x : b) {
            if (x > 0) {
                hashArray[x] = false;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (hashArray[i]) {
                temp[j++] = i;
            }
        }
        return temp;
    }
}
