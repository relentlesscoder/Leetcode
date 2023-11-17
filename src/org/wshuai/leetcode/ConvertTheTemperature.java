package org.wshuai.leetcode;

/**
 * Created by Wei on 11/16/2023.
 * #2469 https://leetcode.com/problems/convert-the-temperature/
 */
public class ConvertTheTemperature {

    // time O(1), space O(1)
    public double[] convertTemperature(double celsius) {
        return new double[] {celsius + 273.15, celsius * 1.80 + 32.0};
    }
}
