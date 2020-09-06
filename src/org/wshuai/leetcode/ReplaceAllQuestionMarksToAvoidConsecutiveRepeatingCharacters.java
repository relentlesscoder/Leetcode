package org.wshuai.leetcode;

/**
 * Created by Wei on 09/06/2020.
 * #1576 https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 */
public class ReplaceAllQuestionMarksToAvoidConsecutiveRepeatingCharacters {

	// time O(n)
	public String modifyString(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		for(int i = 0, j = 0; i < n; i++){
			if(arr[i] == '?'){
				j = i + 1;
				while(j < n && arr[j] == '?'){
					j++;
				}
				j--;
				char c1 = i == 0 ? '#' : arr[i - 1];
				char c2 = j == n - 1 ? '#' : arr[j + 1];
				char a = '#', b = '#';
				for(char k = 'a'; k <= 'z'; k++){
					if(a == '#'){
						if(k != c1 && k != c2){
							a = k;
						}
					}else{
						if(k != c1 && k != c2){
							b = k;
						}
						break;
					}
				}
				for(int x = i, y = 0; x <= j; x++, y = 1 - y){
					arr[x] = y == 0 ? a : b;
				}
				i = j;
			}
		}
		return String.valueOf(arr);
	}
}
