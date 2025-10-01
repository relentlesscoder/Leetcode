package org.wshuai.leetcode;

/**
 * Created by Wei on 09/30/2025.
 * #3280 https://leetcode.com/problems/convert-date-to-binary/
 */
public class ConvertDateToBinary {

    // time O(n), space O(1)
    public String convertDateToBinary(String date) {
        String y = date.substring(0,4);
        String m = date.substring(5,7);
        String d = date.substring(8,10);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toBinaryString(Integer.parseInt(y)));
        sb.append("-");
        sb.append(Integer.toBinaryString(Integer.parseInt(m)));
        sb.append("-");
        sb.append(Integer.toBinaryString(Integer.parseInt(d)));
        return sb.toString();
    }
}
