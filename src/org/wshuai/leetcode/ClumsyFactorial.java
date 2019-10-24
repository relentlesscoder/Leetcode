package org.wshuai.leetcode;

/**
 * Created by Wei on 10/24/2019.
 * #1006 https://leetcode.com/problems/clumsy-factorial/
 */
public class ClumsyFactorial {
	public int clumsy(int N) {
		int cnt = N / 4;
		int rem = N % 4;
		int sum = 0;
		int n = N;
		while(cnt > 0){
			int p = (n) * (n - 1) / (n - 2);
			sum = sum == 0 ? p : sum - p;
			sum += (n - 3);
			n -= 4;
			cnt --;
		}
		if(rem == 1){
			sum = sum == 0 ? n : sum - n;
		}
		if(rem == 2){
			sum = sum == 0 ? n*(n - 1) : sum - n*(n - 1);
		}
		if(rem == 3){
			sum = sum == 0 ? n * (n - 1) / (n - 2)  : sum - n * (n - 1) / (n - 2);
		}
		return sum;
	}
}
