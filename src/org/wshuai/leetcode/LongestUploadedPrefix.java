package org.wshuai.leetcode;

/**
 * Created by Wei on 11/18/2023.
 * #2424 https://leetcode.com/problems/longest-uploaded-prefix/
 */
public class LongestUploadedPrefix {

    // time amortized O(1), space O(n)
    private class LUPrefix {

        private int[] arr;

        private int index;

        public LUPrefix(int n) {
            arr = new int[n + 1];
            index = 0;
        }

        public void upload(int video) {
            arr[video] = 1;
        }

        public int longest() {
            while (index + 1 < arr.length && arr[index + 1] == 1) {
                index++;
            }
            return index;
        }
    }

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */
}
