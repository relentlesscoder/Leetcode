package org.wshuai.algorithm.binaryIndexedTree;

/**
 * Implementation for binary indexed tree for 1 indexed difference array based on
 * https://oi-wiki.org/ds/fenwick/#%E5%8C%BA%E9%97%B4%E5%8A%A0%E5%8C%BA%E9%97%B4%E5%92%8C
 */
public class BinaryIndexedTreeDifferenceArray {

    private final int[] t1;
    private final int[] t2;

    /**
     * Constructor
     * @param diff 1 indexed difference array for original array,
     *             e.g., [4,1,2,1,4] for [4,5,7,8,12]
     */
    public BinaryIndexedTreeDifferenceArray(int[] diff) {
        int n = diff.length;
        t1 = new int[n + 1];
        t2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            t1[i] += diff[i - 1];
            t2[i] += i * diff[i - 1];
            int nextIndex = i + (i & -i);
            if (nextIndex <= t1.length) {
                t1[nextIndex] += t1[i];
                t2[nextIndex] += t2[i];
            }
        }
    }

    /**
     * Get value at the index from the original array
     * @param index 1 indexed index
     * @return Value for that index
     */
    public int get(int index) {
        return sum(t1, index);
    }

    /**
     * Get range sum (inclusive) from the original array
     * @param left Left index
     * @param right Right index
     * @return Sum for range [left, right]
     */
    public int rangeSum(int left, int right) {
        return (right + 1) * sum(t1, right) - left * sum(t1, left - 1)
                - (sum(t2, right) - sum(t2, left - 1));
    }

    /**
     * Update values in the range [left, right] in the original array
     * @param left Left index
     * @param right Right index
     * @param val Delta to update
     */
    public void rangeUpdate(int left, int right, int val) {
        update(left, val);
        update(right + 1, -val);
    }

    private void update(int index, int val) {
        int val1 = index * val;
        while (index < t1.length) {
            t1[index] += val;
            t2[index] += val1;
            index += lowbit(index);
        }
    }

    private int sum(int[] tree, int index) {
        int res = 0;
        while (index > 0) {
            res += tree[index];
            index -= lowbit(index);
        }
        return res;
    }

    private int lowbit(int index) {
        return index & -index;
    }
}
