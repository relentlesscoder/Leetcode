package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 12/17/2023.
 * #2193 https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
 */
public class MinimumNumberOfMovesToMakePalindrome {

    // time O(n * log(n)), space O(n)
    public int minMovesToMakePalindrome(String s) {
        // https://leetcode.cn/problems/minimum-number-of-moves-to-make-palindrome/solutions/1313655/de-dao-hui-wen-chuan-de-zui-shao-cao-zuo-nnis/
        int res = 0, n = s.length();
        int[] freq = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            freq[c - 'a']++;
        }

        // Build left side index array
        List<Integer>[] left = new ArrayList[26], right = new ArrayList[26];
        Arrays.setAll(left, i -> new ArrayList<>());
        Arrays.setAll(right, i -> new ArrayList<>());
        int leftCnt = 0, rightCnt = 0;
        for (int i = 0; i < n; i++) {
            int idx = chars[i] - 'a';
            if (left[idx].size() + 1 <= freq[idx] / 2) {
                left[idx].add(++leftCnt);
                res += i - leftCnt + 1;
            } else {
                right[idx].add(++rightCnt);
            }
        }

        // Hande odd length case
        if (n % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    left[i].add(++leftCnt);
                    break;
                }
            }
        }

        // Build the right side index array based left side indexes
        int m = (n + 1) / 2;
        int[] nums = new int[m];
        for (int i = 0; i < 26; i++) {
            List<Integer> llist = left[i];
            List<Integer> rlist = right[i];
            for (int j = 0; j < rlist.size(); j++) {
                // Greedily use rightmost character in right to match leftmost
                // character in left
                nums[rlist.get(rlist.size() - j - 1) - 1] = llist.get(j);
            }
        }
        // Reverse the string (if later we find pairs with i < j and nums[i] < nums[j]
        // then this step can be removed)
        for (int i = 0, j = m - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        res += reversePairs(nums);
        return res;
    }

    public int reversePairs(int[] nums) {
        int res = 0, n = nums.length;
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();
        int m = sorted.length;
        BIT bit = new BIT(m);
        for (int i = 0; i < n; i++) {
            int index = binarySearch(sorted, nums[i]) + 1;
            res += i - bit.query(index);
            bit.update(index);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private class BIT {

        private int[] tree;

        public BIT(int n) {
            tree = new int[n + 1];
        }

        public void update(int index) {
            while (index < tree.length) {
                tree[index]++;
                index += index & -index;
            }
        }

        public int query(int index) {
            int res = 0;
            while (index > 0) {
                res += tree[index];
                index -= index & -index;
            }
            return res;
        }
    }

    // time O(n * log(n)), space O(n)
    public int minMovesToMakePalindromeMergeSort(String s) {
        int res = 0, n = s.length();
        int[] freq = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            freq[c - 'a']++;
        }

        // Build left side index array
        List<Integer>[] left = new ArrayList[26], right = new ArrayList[26];
        Arrays.setAll(left, i -> new ArrayList<>());
        Arrays.setAll(right, i -> new ArrayList<>());
        int leftCnt = 0, rightCnt = 0;
        for (int i = 0; i < n; i++) {
            int idx = chars[i] - 'a';
            if (left[idx].size() + 1 <= freq[idx] / 2) {
                left[idx].add(++leftCnt);
                res += i - leftCnt + 1;
            } else {
                right[idx].add(++rightCnt);
            }
        }

        // Hande odd length case
        if (n % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    left[i].add(++leftCnt);
                    break;
                }
            }
        }

        // Build the right side index array based left side indexes
        int m = (n + 1) / 2;
        int[] nums = new int[m];
        for (int i = 0; i < 26; i++) {
            List<Integer> llist = left[i];
            List<Integer> rlist = right[i];
            for (int j = 0; j < rlist.size(); j++) {
                // Greedily use rightmost character in right to match leftmost
                // character in left
                nums[rlist.get(rlist.size() - j - 1) - 1] = llist.get(j);
            }
        }
        // Reverse the string (if later we find pairs with i < j and nums[i] < nums[j]
        // then this step can be removed)
        for (int i = 0, j = m - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        res += reversePairsMergeSort(nums);
        return res;
    }

    public int reversePairsMergeSort(int[] nums) {
        int n = nums.length;
        return mergeSort(nums, 0, n - 1);
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        int left = mergeSort(nums, low, mid);
        int right = mergeSort(nums, mid + 1, high);
        int middle = merge(nums, low, high);
        return left + right + middle;
    }

    private int merge(int[] nums, int low, int high) {
        int res = 0;
        int mid = low + (high - low) / 2;
        int leftIndex = low;
        int rightIndex = mid + 1;
        int nextIndex = mid + 1;
        int idx = 0;
        int[] temp = new int[high - low + 1];
        while (leftIndex <= mid) {
            while (nextIndex <= high && nums[leftIndex] > nums[nextIndex]) {
                nextIndex++;
            }
            res += nextIndex - mid - 1;
            // sorting
            while (rightIndex <= high && nums[rightIndex] < nums[leftIndex]) {
                temp[idx++] = nums[rightIndex++];
            }
            temp[idx++] = nums[leftIndex++];
        }
        while (rightIndex <= high) {
            // if right part still has elements left
            temp[idx++] = nums[rightIndex++];
        }
        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
        return res;
    }

    // time O(n^2), space O(n)
    public int minMovesToMakePalindromeRecursion(String s) {
        if (s.length() <= 2) {
            return 0;
        }
        // Greedily find the rightmost character that matches the fist character
        // and swap it with the last character
        int right = s.length() - 1;
        char c = s.charAt(0);
        while (right > 0 && s.charAt(right) != c) {
            right--;
        }
        // char c is the only unique character, we need to move it to the center
        if (right == 0) {
            return ((s.length() - 1) >> 1) +
                    minMovesToMakePalindrome(s.substring(1));
        } else {
            // Remove the char pair from the string and recursively find the minimum
            // operations for the new string
            return s.length() - 1 - right +
                    minMovesToMakePalindrome(s.substring(1, right) +
                            s.substring(right + 1));
        }
    }
}
