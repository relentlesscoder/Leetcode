package org.wshuai.leetcode;

/**
 * Created by Wei on 08/11/2020.
 * #1538 https://leetcode.com/problems/guess-the-majority-in-a-hidden-array/
 */
public class GuessTheMajorityInAHiddenArray {

    // time O(n), space O(n)
    // https://leetcode.com/problems/guess-the-majority-in-a-hidden-array/discuss/777349/Java-O(n)-Time-O(1)-Space-Use-Map-to-Store-Transition-Sequence-Detailed
    public int guessMajority(ArrayReader reader) {
        int[][] map = new int[5][5];
        map[0][2] = map[2][0] = map[2][4] = map[4][2] = 1;
        int n = reader.length();
        int[] arr = new int[n];
        int prev = reader.query(0, 1, 2, 3);
        int cur = reader.query(1, 2, 3, 4);
        arr[4] = arr[0] ^ map[prev][cur];
        cur = reader.query(0, 2, 3, 4);
        arr[1] = arr[4] ^ map[prev][cur];
        cur = reader.query(0, 1, 3, 4);
        arr[2] = arr[4] ^ map[prev][cur];
        cur = reader.query(0, 1, 2, 4);
        arr[3] = arr[4] ^ map[prev][cur];
        prev = reader.query(1, 2, 3, 4);
        for (int i = 5; i < n; i++) {
            cur = reader.query(i - 3, i - 2, i - 1, i);
            arr[i] = arr[i - 4] ^ map[prev][cur];
            prev = cur;
        }
        int[] count = new int[2];
        for (int a : arr) {
            count[a]++;
        }
        if (count[0] == count[1]) {
            return -1;
        }
        int major = count[0] > count[1] ? 0 : 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == major) {
                return i;
            }
        }
        return -1;
    }

    // This is the ArrayReader's API interface.
    // You should not implement it, or speculate about its implementation
    private interface ArrayReader {
        // Compares 4 different elements in the array
        // return 4 if the values of the 4 elements are the same (0 or 1).
        // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
        // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
        public int query(int a, int b, int c, int d);

        // Returns the length of the array
        public int length();
    }
}
