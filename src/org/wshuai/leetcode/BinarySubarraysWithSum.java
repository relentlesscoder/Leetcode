package org.wshuai.leetcode;

/**
 * Created by Wei on 11/7/19.
 * #930 https://leetcode.com/problems/binary-subarrays-with-sum/
 */
public class BinarySubarraysWithSum {
	public int numSubarraysWithSum(int[] A, int S) {
		int res = 0;
		if(A.length == 0){
			return res;
		}
		if(S == 0){
			int count = 0;
			for(int i = 0; i < A.length; i++){
				if(A[i] == 0){
					count++;
				}else{
					if(count > 0){
						res += (1 + count) * count / 2;
					}
					count = 0;
				}
			}
			if(count > 0){
				res += (1 + count) * count / 2;
			}
			return res;
		}else{
			int i = 0;
			int j = 0;
			int sum = 0;
			while(j < A.length){
				sum += A[j];
				if(sum == S){
					int l = 1;
					int r = 1;
					while(j < A.length - 1 && A[j + 1] == 0){
						r++;
						j++;
					}
					j++;
					while(A[i] == 0){
						l++;
						i++;
					}
					sum--;
					i++;
					res += l * r;
				}else{
					j++;
				}
			}
			return res;
		}
	}
}
