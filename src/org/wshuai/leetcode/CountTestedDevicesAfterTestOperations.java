package org.wshuai.leetcode;

/**
 * Created by Wei on 12/23/2023.
 * #2960 https://leetcode.com/problems/count-tested-devices-after-test-operations/
 */
public class CountTestedDevicesAfterTestOperations {

    // time O(n), space O(1)
    public int countTestedDevices(int[] batteryPercentages) {
        // 在下解的基础上优化，因为所有的操作是在范围 [i + 1, n - 1] 减 1 所以
        // 可以用一个变量代替差分数组。
        int res = 0, n = batteryPercentages.length;
        for (int i = 0, sum = 0; i < n; i++) {
            int curr = batteryPercentages[i] + sum;
            if (curr > 0) {
                res++;
                sum--;
            }
        }
        return res;
    }

    // time O(n), space O(n)
    public int countTestedDevicesDiffArray(int[] batteryPercentages) {
        // 差分数组应用
        int res = 0, n = batteryPercentages.length;
        int[] diff = new int[n + 1];
        for (int i = 0, sum = 0; i < n; i++) {
            sum += diff[i];
            int curr = batteryPercentages[i] + sum;
            if (curr > 0) {
                res++;
                diff[i + 1]--;
                diff[n]++;
            }
        }
        return res;
    }
}
