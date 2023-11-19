package org.wshuai.leetcode;

/**
 * Created by Wei on 11/19/2023.
 * #2043 https://leetcode.com/problems/simple-bank-system/
 */
public class SimpleBankSystem {

    private class Bank {

        private int accounts;
        private long[] balance;

        // time O(1), space O(n)
        public Bank(long[] balance) {
            this.accounts = balance.length;
            this.balance = balance;
        }

        // time O(1), space O(1)
        public boolean transfer(int account1, int account2, long money) {
            if (!validateAccount(account1) || !validateAccount(account2) || this.balance[account1 - 1] < money) {
                return false;
            }
            this.balance[account1 - 1] -= money;
            this.balance[account2 - 1] += money;
            return true;
        }

        // time O(1), space O(1)
        public boolean deposit(int account, long money) {
            if (!validateAccount(account)) {
                return false;
            }
            this.balance[account - 1] += money;
            return true;
        }

        // time O(1), space O(1)
        public boolean withdraw(int account, long money) {
            if (!validateAccount(account) || this.balance[account - 1] < money) {
                return false;
            }
            this.balance[account - 1] -= money;
            return true;
        }

        private boolean validateAccount(int account) {
            return account >= 1 && account <= this.accounts;
        }
    }

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */
}
