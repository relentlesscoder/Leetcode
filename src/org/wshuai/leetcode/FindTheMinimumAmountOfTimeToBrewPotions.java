package org.wshuai.leetcode;

/**
 * Created by Wei on 10/22/2025.
 * #3494 https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/
 */
public class FindTheMinimumAmountOfTimeToBrewPotions {

    // time O(m * n), space O(n)
    public long minTimeSpaceOptimizedDP(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        long[] lastFinishTime = new long[n];
        for (int i = 0; i < m; i++) {
            long endTime = 0;
            for (int j = 0; j < n; j++) {
                endTime = Math.max(endTime, lastFinishTime[j]) + skill[j] * mana[i];
            }
            lastFinishTime[n - 1] = endTime;
            for (int k = n - 2; k >= 0; k--) {
                lastFinishTime[k] = lastFinishTime[k + 1] - skill[k + 1] * mana[i];
            }
        }
        return lastFinishTime[n - 1];
    }

    public long minTimeDP(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;
        // lastFinishTime[i][j] is the time the jth wizard finished the (i - 1)th potion
        long[][] lastFinishTime = new long[m + 1][n];
        for (int i = 1; i <= m; i++) {
            long endTime = 0;
            for (int j = 0; j < n; j++) { // calculate the finish time (for all wizards) of the current potion
                // max(endTime, lastFinishTime[i - 1][j] - ensure the wizard already finished last potion
                endTime = Math.max(endTime, lastFinishTime[i - 1][j]) + skill[j] * mana[i - 1];
            }
            lastFinishTime[i][n - 1] = endTime;
            for (int k = n - 2; k >= 0; k--) { // calculate backward for the finish time for each wizard
                lastFinishTime[i][k] = lastFinishTime[i][k + 1] - skill[k + 1] * mana[i - 1];
            }
        }
        return lastFinishTime[m][n - 1];
    }

    // time O(m * n * log(sum(skill))), space O(1)
    public long minTimeBinarySearch(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length, totalSkills = 0;
        long endTime = 0, nextStartTime = 0;
        for (int i = 0; i < n; i++) { // O(n)
            totalSkills += skill[i];
        }
        for (int i = 0; i < m; i++) { // O(m)
            endTime = nextStartTime + 1L * mana[i] * totalSkills;
            if (i < m - 1) {
                nextStartTime = findNextStartTime(i, skill, mana, nextStartTime, endTime);
            }
        }
        return endTime;
    }

    // time O(n * log(sum(skill)))
    private long findNextStartTime(int curr, int[] skill, int[] mana, long start, long end) {
        long low = start, high = end;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (isValid(mid, curr, mana, skill, start)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // time O(n)
    private boolean isValid(long nextStartTime, int curr, int[] mana, int[] skill, long startTime) {
        int nextMana = mana[curr + 1];
        long currentEndTime = startTime;
        for (int i = 0; i < skill.length; i++) {
            currentEndTime += skill[i] * mana[curr];
            if (nextStartTime < currentEndTime) {
                return false;
            }
            nextStartTime += skill[i] * nextMana;
        }
        return true;
    }
}
