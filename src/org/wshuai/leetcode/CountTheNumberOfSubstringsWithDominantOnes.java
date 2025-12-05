package org.wshuai.leetcode;

/**
 * Created by Wei on 10/01/2025.
 * #3234 https://leetcode.com/problems/count-the-number-of-substrings-with-dominant-ones/
 */
public class CountTheNumberOfSubstringsWithDominantOnes {

    // time O(n * sqrt(n)), space O(n)
    public int numberOfSubstrings(String s) {
        int res = 0, n = s.length(), m = 0;
        char[] arr = s.toCharArray();
        int[] zeroIndexes = new int[n + 1]; // array stores indexes of zeros
        for (int i = 0; i < n; i++) {
            if (arr[i] == '0') {
                zeroIndexes[m++] = i;
            }
        }
        zeroIndexes[m] = n; // sentinel
        int totalOnes = n - m;
        // nextZeroIndex is the index of next zero to the current left boundary of the substring,
        // so initially the next zero is zeroIndexes[0]
        int nextZeroIndex = 0;
        for (int left = 0; left < n; left++) {
            if (arr[left] == '1') {
                // add all `ones` substring start with arr[left] and ends at [left, next zero index - 1]
                // for example [1,1,1,1,0], count of all `ones` substring is 4
                res += zeroIndexes[nextZeroIndex] - left;
            }
            for (int z = nextZeroIndex; z < m; z++) { // iterate all zero indexes from nextZeroIndex
                int zeroCount = z - nextZeroIndex + 1; // count zeros
                int zeroCountSquare = zeroCount * zeroCount;
                // stop early if square of current zeros are already greater than total ones
                // since it is impossible to get more ones
                if (zeroCountSquare > totalOnes) {
                    break;
                }
                // count of ones = count of characters - count of zeros
                // count of characters = zeroIndexes[z] - left + 1
                // count of zeros = z - nextZeroIndex + 1;
                int oneCount = zeroIndexes[z] - left - (z - nextZeroIndex);
                // two scenarios:
                // 1. zeroCount^2 <= oneCount: all substrings starts with left and ends with
                //    [zeroIndexes[z], zeroIndexes[z + 1] - 1] are valid
                //    example: [1,1,0,1,1,0,1,1,1,1,0] all substrings starts with 0 and ends with [6,9] are valid
                // 2. zeroCount^2 > oneCount: then we need add at least zeroCountSquare - oneCount
                //    ones to the substring to make it valid, so we need to deduct them from the ones
                //    in step #1
                //    example: [1,1,0,0,1,1,1,1,0] only substrings starts with 0 and ends with [6,7] are valid
                res += Math.max(zeroIndexes[z + 1] - zeroIndexes[z] - Math.max(zeroCountSquare - oneCount, 0), 0);
            }
            // advance next zero if current character is '0'
            // note that zeros left to nextZeroIndex will not be iterated
            // in future processing
            if (arr[left] == '0') {
                nextZeroIndex++;
            }
        }
        return res;
    }

    // time O(n * sqrt(n)), space O(n)
    public int numberOfSubstringsRightBoundary(String s) {
        // same idea but process with right boundary
        int res = 0, n = s.length(), m = 1;
        char[] arr = s.toCharArray();
        int[] zeroIndexes = new int[n + 1];
        zeroIndexes[0] = -1;
        int totalOnes = 0, lastZeroIndex = 1;
        for (int right = 0; right < n; right++) {
            if (arr[right] == '0') {
                zeroIndexes[lastZeroIndex++] = right;
            } else {
                res += right - zeroIndexes[lastZeroIndex - 1];
                totalOnes++;
            }
            for (int z = lastZeroIndex - 1; z > 0 && (lastZeroIndex - z) * (lastZeroIndex - z) <= totalOnes; z--) {
                int zeroCount = lastZeroIndex - z;
                int oneCount = right - zeroIndexes[z] + 1 - zeroCount;
                res += Math.max(zeroIndexes[z] - zeroIndexes[z - 1] - Math.max(zeroCount * zeroCount - oneCount, 0), 0);
            }
        }
        return res;
    }
}
