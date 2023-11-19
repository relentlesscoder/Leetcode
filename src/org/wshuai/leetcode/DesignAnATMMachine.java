package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2241 https://leetcode.com/problems/design-an-atm-machine/
 */
public class DesignAnATMMachine {

    // time O(1), space O(1)
    private class ATM {

        private long[] banknotes;

        public ATM() {
            banknotes = new long[5];
        }

        public void deposit(int[] banknotesCount) {
            for (int i = 0; i < 5; i++) {
                banknotes[i] += banknotesCount[i];
            }
        }

        public int[] withdraw(int amount) {
            int[] res = new int[5];
            long[] temp = banknotes.clone();
            if (amount >= 500 && temp[4] > 0) {
                int used = (int)Math.min(temp[4], amount / 500);
                res[4] = used;
                temp[4] -= used;
                amount -= 500 * used;
            }
            if (amount >= 200 && temp[3] > 0) {
                int used = (int)Math.min(temp[3], amount / 200);
                res[3] = used;
                temp[3] -= used;
                amount -= 200 * used;
            }
            if (amount >= 100 && temp[2] > 0) {
                int used = (int)Math.min(temp[2], amount / 100);
                res[2] = used;
                temp[2] -= used;
                amount -= 100 * used;
            }
            if (amount >= 50 && temp[1] > 0) {
                int used = (int)Math.min(temp[1], amount / 50);
                res[1] = used;
                temp[1] -= used;
                amount -= 50 * used;
            }
            if (amount >= 20 && temp[0] > 0) {
                int used = (int)Math.min(temp[0], amount / 20);
                res[0] = used;
                temp[0] -= used;
                amount -= 20 * used;
            }
            if (amount == 0) {
                banknotes = temp;
                return res;
            }
            return new int[]{-1};
        }
    }

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */
}
