package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/2023.
 * #2960 https://leetcode.com/problems/count-tested-devices-after-test-operations/
 */
public class CountTestedDevicesAfterTestOperations {

    // time O(n), space O(1)
    public int countTestedDevices(int[] batteryPercentages) {
        int res = 0, deduct = 0, n = batteryPercentages.length;
        for (int i = 0; i < n; i++) {
            int curr = Math.max(0, batteryPercentages[i] - deduct);
            if (curr > 0) {
                res++;
                deduct++;
            }
        }
        return res;
    }
}
