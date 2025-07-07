package org.wshuai.leetcode;

/**
 * Created by Wei on 07/06/2025.
 * #2698 https://leetcode.com/problems/find-the-punishment-number-of-an-integer/
 */
public class FindThePunishmentNumberOfAnInteger {

    // time O(n * log_10(n^2)), space O(log_10(n^2))
    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            if (canPartition(square, i)) {
                res += square;
            }
        }
        return res;
    }

    private boolean canPartition(int num, int target) {
        if (num == target) {
            return true;
        }
        if (target < 0 || num < target) {
            return false;
        }
        // nums <= 1,000
        return canPartition(num / 10, target - (num % 10))
                || canPartition(num / 100, target - (num % 100))
                || canPartition(num / 1000, target - (num % 1000));
    }

    // time O(n * log_10(n^2)), space O(log_10(n^2))
    public int punishmentNumberStringRecursion(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int square = i * i;
            if (canPartitionStringRecursion(Integer.toString(square), i)) {
                res += square;
            }
        }
        return res;
    }

    private boolean canPartitionStringRecursion(String num, int target) {
        if (num.isEmpty() && target == 0) {
            return true;
        }
        if (target < 0) {
            return false;
        }
        for (int i = 0; i < num.length(); i++) {
            String left = num.substring(0, i + 1),
                    right = num.substring(i + 1);
            int leftNum = Integer.parseInt(left);
            if (canPartitionStringRecursion(right, target - leftNum)) {
                return true;
            }
        }
        return false;
    }
}
