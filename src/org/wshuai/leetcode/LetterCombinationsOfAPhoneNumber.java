package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wei on 10/28/2016.
 * #17 https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsOfAPhoneNumber {
  private static final char[] KEYS2 = new char[]{'a','b','c'};
  private static final char[] KEYS3 = new char[]{'d','e','f'};
  private static final char[] KEYS4 = new char[]{'g','h','i'};
  private static final char[] KEYS5 = new char[]{'j','k','l'};
  private static final char[] KEYS6 = new char[]{'m','n','o'};
  private static final char[] KEYS7 = new char[]{'p','q','r','s'};
  private static final char[] KEYS8 = new char[]{'t','u','v'};
  private static final char[] KEYS9 = new char[]{'w','x','y','z'};

  public List<String> letterCombinations(String digits) {
    List<String> s = new ArrayList<String>();
    if(digits == null || digits.isEmpty()){
      return s;
    }
    for(int i = 0; i < digits.length(); i++){
      switch(digits.charAt(i)){
        case '2':
          s = appendToList(s, KEYS2);
          break;
        case '3':
          s = appendToList(s, KEYS3);
          break;
        case '4':
          s = appendToList(s, KEYS4);
          break;
        case '5':
          s = appendToList(s, KEYS5);
          break;
        case '6':
          s = appendToList(s, KEYS6);
          break;
        case '7':
          s = appendToList(s, KEYS7);
          break;
        case '8':
          s = appendToList(s, KEYS8);
          break;
        case '9':
          s = appendToList(s, KEYS9);
          break;
        default:
          break;
      }
    }
    return s;
  }

  private List<String> appendToList(List<String> s, char[] chars){
    List<String> lst = new ArrayList<String>();

    if(s.size() == 0){
      for(int j = 0; j < chars.length; j++){
        lst.add(Character.toString(chars[j]));
      }
      return lst;
    }

    for(int i = 0; i < s.size(); i++){
      for(int j = 0; j < chars.length; j++){
        String x = s.get(i);
        lst.add(x + chars[j]);
      }
    }
    return lst;
  }
}
