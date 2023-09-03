package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 09/03/2023.
 * #2194 https://leetcode.com/problems/cells-in-a-range-on-an-excel-sheet/description/
 */
public class CellsInARangeOnAnExcelSheet {

    // time O(m*n), space O(1)
    public List<String> cellsInRange(String s) {
        List<String> res = new ArrayList<>();
        int row1 = s.charAt(1) - '0', row2 = s.charAt(4) - '0';
        char col1 = s.charAt(0), col2 = s.charAt(3);
        for (char c = col1; c <= col2; c++) {
            for (int r = row1; r <= row2; r++) {
                res.add("" + c + r);
            }
        }
        return res;
    }
}
