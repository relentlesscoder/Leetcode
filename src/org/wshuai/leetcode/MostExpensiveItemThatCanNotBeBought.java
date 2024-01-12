package org.wshuai.leetcode;

/**
 * Created by Wei on 01/11/2024.
 * #2979 https://leetcode.com/problems/most-expensive-item-that-can-not-be-bought/
 */
public class MostExpensiveItemThatCanNotBeBought {

    // time O(1), space O(1)
    public int mostExpensiveItem(int primeOne, int primeTwo) {
        return primeOne * primeTwo - primeOne - primeTwo;
    }
}
