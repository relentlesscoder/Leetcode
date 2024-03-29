package org.wshuai.leetcode;

/**
 * Created by Wei on 01/01/2020.
 * #0479 https://leetcode.com/problems/largest-palindrome-product/
 */
public class LargestPalindromeProduct {
	public int largestPalindrome(int n) {
		if (n==1) return 9;
		int max=(int)Math.pow(10, n)-1;
		for (int v=max-1;v>max/10;v--) {
			long u=Long.valueOf(v+new StringBuilder().append(v).reverse().toString());
			for (long x=max;x*x>=u;x--)
				if (u%x==0)
					return (int)(u%1337);
		}
		return 0;
	}
}
