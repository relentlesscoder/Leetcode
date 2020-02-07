package org.wshuai.leetcode;

/**
 * Created by Wei on 09/19/2016.
 * #0345 https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
public class ReverseVowelsOfAString {
	// time O(n)
	public String reverseVowels(String s) {
		if(s == null || s.isEmpty()){
			return "";
		}
		int i = 0, j = s.length() - 1;
		char[] arr = s.toCharArray();
		while(i < j){
			boolean b1 = isVowel(arr[i]), b2 = isVowel(arr[j]);
			if(!b1){
				i++;
			}else if(!b2){
				j--;
			}else{
				char temp = arr[i];
				arr[i++] = arr[j];
				arr[j--] = temp;
			}
		}
		return new String(arr);
	}

	private boolean isVowel(char c){
		return c == 'a'
				|| c == 'e'
				|| c == 'i'
				|| c == 'o'
				|| c == 'u'
				|| c == 'A'
				|| c == 'E'
				|| c == 'I'
				|| c == 'O'
				|| c == 'U';
	}
}
