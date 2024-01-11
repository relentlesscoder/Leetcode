package org.wshuai.leetcode;

/**
 * Created by Wei on 01/10/2024.
 * #2383 https://leetcode.com/problems/minimum-hours-of-training-to-win-a-competition/
 */
public class MinimumHoursOfTrainingToWinACompetition {

    // time O(n), space O(1)
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int res = 0, eng = initialEnergy, exp = initialExperience;
        for (int i = 0; i < energy.length; i++) {
            if (energy[i] >= eng) {
                res += energy[i] - eng + 1;
                eng += energy[i] - eng + 1;
            }
            if (experience[i] >= exp) {
                res += experience[i] - exp + 1;
                exp += experience[i] - exp + 1;
            }
            eng -= energy[i];
            exp += experience[i];
        }
        return res;
    }

    // time O(n * log(n)), space O(1)
    public int minNumberOfHoursBinarySearch(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        return searchMinEnergy(energy, initialEnergy) + searchMinExp(experience, initialExperience);
    }

    private int searchMinEnergy(int[] energy, int initialEnergy) {
        int low = 0, high = (int)1e5;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canWinWithEnergy(energy, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return Math.max(low - initialEnergy, 0);
    }

    private boolean canWinWithEnergy(int[] energy, int mid) {
        int curr = mid;
        for (int i = 0; i < energy.length; i++) {
            if (curr <= energy[i]) {
                return false;
            }
            curr -= energy[i];
        }
        return true;
    }

    private int searchMinExp(int[] exp, int initialExp) {
        int low = 0, high = (int)1e5;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canWinWithExp(exp, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return Math.max(low - initialExp, 0);
    }

    private boolean canWinWithExp(int[] exp, int mid) {
        int curr = mid;
        for (int i = 0; i < exp.length; i++) {
            if (curr <= exp[i]) {
                return false;
            }
            curr += exp[i];
        }
        return true;
    }
}
