package org.wshuai.leetcode;

/**
 * Created by Wei on 01/09/2024.
 * #2806 https://leetcode.com/problems/account-balance-after-rounded-purchase/
 */
public class AccountBalanceAfterRoundedPurchase {

    // time O(1), space O(1)
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int actualAmount = 0, d = purchaseAmount % 10;
        if (d >= 5) {
            actualAmount = purchaseAmount / 10 * 10 + 10;
        } else {
            actualAmount = purchaseAmount / 10 * 10;
        }
        return 100 - actualAmount;
    }
}
