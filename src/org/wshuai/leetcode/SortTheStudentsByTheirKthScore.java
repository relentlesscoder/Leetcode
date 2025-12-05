package org.wshuai.leetcode;

import java.util.Arrays;

/**
 * Created by Wei on 11/20/2023.
 * #2545 https://leetcode.com/problems/sort-the-students-by-their-kth-score/
 */
public class SortTheStudentsByTheirKthScore {

    // time O(m * log(m)), space O(m)
    public int[][] sortTheStudents(int[][] score, int k) {
        quickSort(score, k, 0, score.length - 1);
        return score;
    }

    private void quickSort(int[][] score, int k, int left, int right) {
        if (left < right) {
            int pivot = partition(score, k, left, right);
            quickSort(score, k, left, pivot - 1);
            quickSort(score, k, pivot + 1, right);
        }
    }

    private int partition(int[][] score, int k, int left, int right) {
        int pivot = score[right][k];
        int i = left;
        for (int j = left; j < right; j++) {
            if (score[j][k] > pivot) {
                int[] temp = score[i];
                score[i] = score[j];
                score[j] = temp;
                i++;
            }
        }
        int[] temp = score[right];
        score[right] = score[i];
        score[i] = temp;
        return i;
    }

    // time O(m * log(m)), space O(m)
    public int[][] sortTheStudentsSorting(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> Integer.compare(b[k], a[k]));
        return score;
    }
}
