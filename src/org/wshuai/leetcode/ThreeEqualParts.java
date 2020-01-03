package org.wshuai.leetcode;

/**
 * Created by Wei on 1/1/2020.
 * #927 https://leetcode.com/problems/three-equal-parts/
 */
public class ThreeEqualParts {
	public int[] threeEqualParts(int[] A) {
		int[] def = new int[]{-1, -1};
		int oneCount = 0;
		for(int a : A){
			oneCount += a == 1 ? 1 : 0;
		}
		if(oneCount == 0){
			return new int[]{0, 2};
		}
		if(oneCount % 3 != 0){
			return def;
		}
		oneCount /= 3;
		int oneFromEnd = 0;
		int thirdPartFirstOne = A.length - 1;
		for(; thirdPartFirstOne >= 0; thirdPartFirstOne--){
			oneFromEnd += A[thirdPartFirstOne] == 1 ? 1 : 0;
			if(oneFromEnd == oneCount){
				break;
			}
		}
		int firstPartEndPos = tryMatch(A, 0, thirdPartFirstOne);
		if(firstPartEndPos == -1){
			return def;
		}
		int secondPartEndPos = tryMatch(A, firstPartEndPos + 1, thirdPartFirstOne);
		if(secondPartEndPos == -1){
			return def;
		}
		return new int[]{firstPartEndPos, secondPartEndPos + 1};
	}

	private int tryMatch(int[] A, int i, int k){
		while(A[i] == 0){
			i++;
		}
		int j = k;
		while(i < k && j < A.length){
			if(A[i++] != A[j++]){
				return -1;
			}
		}
		return j == A.length ? i - 1 : -1;
	}
}
