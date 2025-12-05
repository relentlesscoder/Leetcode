package org.wshuai.leetcode;

/**
 * Created by Wei on 11/07/2025.
 * #3208 https://leetcode.com/problems/alternating-groups-ii/
 */
public class AlternatingGroupsII {

    // time O(n + k), space O(1)
    public int numberOfAlternatingGroupsOnePass(int[] colors, int k) {
        int res = 0, n = colors.length, count = 1;
        // First pass to find all contiguous alternating subarrays, for each
        // such groups, the number of alternating groups it contains (head of
        // each group is in the subarray) is Math.max(count - k + 1, 0)
        for (int i = 1; i < n + k - 1; i++) {
            if (colors[i % n] == 1 - colors[(i - 1) % n]) {
                count++;
            } else {
                res += Math.max(count - k + 1, 0);
                count = 1;
            }
        }
        return res + Math.max(count - k + 1, 0);
    }

    // time O(n + k), space O(1)
    public int numberOfAlternatingGroupsTwoPass(int[] colors, int k) {
        int res = 0, n = colors.length, count = 1;
        // First pass to find all contiguous alternating subarrays, for each
        // such groups, the number of alternating groups it contains (head of
        // each group is in the subarray) is Math.max(count - k + 1, 0)
        for (int i = 1; i < n; i++) {
            if (colors[i] == 1 - colors[i - 1]) {
                count++;
            } else {
                res += Math.max(count - k + 1, 0);
                count = 1;
            }
        }
        // Try extending the last contiguous alternating subarray.
        // Note that alternating group with head not in the first pass
        // needs to be excluded to avoid double counting, so we only
        // iterate up to index k - 2.
        for (int j = 0; j < k - 1; j++) {
            if (colors[j] == 1 - colors[(j - 1 + n) % n]) {
                count++;
            } else {
                break;
            }
        }
        return res + Math.max(count - k + 1, 0);
    }
}
