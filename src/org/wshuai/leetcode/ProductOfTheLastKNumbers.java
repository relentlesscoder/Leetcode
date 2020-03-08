package org.wshuai.leetcode;

/**
 * Created by Wei on 02/18/2020.
 * #1352 https://leetcode.com/problems/product-of-the-last-k-numbers/
 */
public class ProductOfTheLastKNumbers {
	private int lastIndex, lastZeroIndex;
	private int[] prefixProduct;

	public ProductOfTheLastKNumbers() {
		lastIndex = lastZeroIndex = -1;
		prefixProduct = new int[40_001];
		prefixProduct[0] = 1;
	}

	public void add(int num) {
		lastIndex++;
		prefixProduct[lastIndex + 1] = num == 0 ? 1 : prefixProduct[lastIndex] * num;
		if(num == 0){
			lastZeroIndex = lastIndex;
		}
	}

	public int getProduct(int k) {
		if(lastIndex - k + 1 <= lastZeroIndex){
			return 0;
		}
		return prefixProduct[lastIndex + 1] / prefixProduct[lastIndex - k + 1];
	}
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
