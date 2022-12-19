package org.demo.core;

public class Board {

    public static final int LOWER_BOUND = 0;
    public static final int UPPER_BOUND = 4;

    private static boolean[][] cell = new boolean[5][5];

    public static boolean occupy(int i, int j) {
        if (i >= LOWER_BOUND && i <= UPPER_BOUND && j >= LOWER_BOUND && j <= UPPER_BOUND) {
            cell[i][j] = true;
            return true;
        } else {
            return false;
        }
    }

    public static boolean release(int i, int j) {
        if (i >= LOWER_BOUND && i <= UPPER_BOUND && j >= LOWER_BOUND && j <= UPPER_BOUND) {
            cell[i][j] = false;
            return true;
        } else {
            return false;
        }
    }
}
