package org.wshuai.leetcode;

/**
 * Created by Wei on 03/02/2021.
 * #1764 https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array/
 */
public class FormArrayByConcatenatingSubarraysOfAnotherArray {

    // time O(n*m)
    public boolean canChoose(int[][] groups, int[] nums) {
        int numsIndex = 0, groupIndex = 0;
        while(numsIndex < nums.length && groupIndex < groups.length){
            int matched = 0;
            while(numsIndex + matched < nums.length
                    && matched < groups[groupIndex].length
                    && nums[numsIndex + matched] == groups[groupIndex][matched]){
                matched++;
            }
            if(matched == groups[groupIndex].length){
                groupIndex++;
                numsIndex += matched;
            }else{
                numsIndex++;
            }
        }
        return groupIndex == groups.length;
    }
}
