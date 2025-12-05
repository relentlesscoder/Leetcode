package org.wshuai.leetcode;

/**
 * Created by Wei on 10/03/2025.
 * #3484 https://leetcode.com/problems/design-spreadsheet/
 */
public class DesignSpreadsheet {

    private int[][] grid;

    public DesignSpreadsheet(int rows) {
        // if row is big, consider using HashMap with key "26 * i + j" to uniquely identify a cell
        grid = new int[rows][26];
    }

    // time O(1), space O(1)
    public void setCell(String cell, int value) {
        grid[Integer.parseInt(cell.substring(1)) - 1][cell.charAt(0) - 'A'] = value;
    }

    // time O(1), space O(1)
    public void resetCell(String cell) {
        grid[Integer.parseInt(cell.substring(1)) - 1][cell.charAt(0) - 'A'] = 0;
    }

    // time O(1), space O(1)
    public int getValue(String formula) {
        int plus = formula.indexOf("+");
        String first = formula.substring(1, plus);
        String second = formula.substring(plus + 1);
        return getCellValue(first) + getCellValue(second);
    }

    private int getCellValue(String s) {
        if (s.charAt(0) >= 'A' && s.charAt(0) <= 'Z') {
            return grid[Integer.parseInt(s.substring(1)) - 1][s.charAt(0) - 'A'];
        } else {
            return Integer.parseInt(s);
        }
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
