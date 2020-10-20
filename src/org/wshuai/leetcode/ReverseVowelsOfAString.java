package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0345 https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class ReverseVowelsOfAString {

	// time O(n)
	public String reverseVowels(String s) {
		if(s == null || s.length() == 0){
			return s;
		}
		int n = s.length(), left = 0, right = n - 1;
		char[] arr = s.toCharArray();
		while(left < right){
			while(left < right && !isVowel(arr[left])){
				left++;
			}
			while(left < right && !isVowel(arr[right])){
				right--;
			}
			char temp = arr[left];
			arr[left++] = arr[right];
			arr[right--] = temp;
		}
		return String.valueOf(arr);
	}

	private boolean isVowel(char c){
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
				|| c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
