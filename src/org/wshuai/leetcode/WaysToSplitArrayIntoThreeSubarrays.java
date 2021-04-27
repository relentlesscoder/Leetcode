package org.wshuai.leetcode;

/**
 * Created by Wei on 04/27/2021.
 * #1712 https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/
 */
public class WaysToSplitArrayIntoThreeSubarrays {

    private static final int MOD = 1_000_000_007;

    // time O(n*log(n)), space O(n)
    public int waysToSplit(int[] nums) {
        int res = 0, n = nums.length;
        int[] prefix = new int[n + 1];
        for(int i = 1; i <= n; i++){
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        for(int i = 1; i < n - 1; i++){
            if(prefix[i] > (prefix[n] - prefix[i]) / 2){
                break;
            }
            int left = findLeft(prefix, prefix[i], n, i);
            int right = findRight(prefix, n, i);
            if(left == -1 || right == -1){
                continue;
            }
            res = (res + (right - left + 1) % MOD) % MOD;
        }
        return res;
    }

    private int findLeft(int[] prefix, int leftSum, int n, int index){
        int left = index, right = n - 2;
        while(left < right){
            int mid = left + (right - left) / 2;
            int sum = prefix[mid + 1] - prefix[index];
            if(sum < leftSum){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return prefix[left + 1] - prefix[index] >= leftSum ? left : -1;
    }

    private int findRight(int[] prefix, int n, int index){
        int left = index, right = n - 2;
        while(left < right){
            int mid = left + (right - left + 1) / 2;
            int midSum = prefix[mid + 1] - prefix[index], rightSum = prefix[prefix.length - 1] - prefix[mid + 1];
            if(midSum > rightSum){
                right = mid - 1;
            }else{
                left = mid;
            }
        }
        return prefix[prefix.length - 1] - prefix[left + 1] >= prefix[left + 1] - prefix[index] ? left : -1;
    }
}
