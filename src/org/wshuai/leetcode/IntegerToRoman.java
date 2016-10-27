package org.wshuai.leetcode;

/**
 * Created by Wei on 8/13/2016.
 * #12 https://leetcode.com/problems/integer-to-roman/
 */
public class IntegerToRoman {
  public static String intToRoman(int num) {
    StringBuilder output = new StringBuilder();
    int t = num/1000;
    num = num%1000;
    for(int i = 0; i < t; i++){
      output.append("M");
    }

    t = num/100;
    num = num%100;
    switch(t){
      case 1:
        output.append("C");
        break;
      case 2:
        output.append("CC");
        break;
      case 3:
        output.append("CCC");
        break;
      case 4:
        output.append("CD");
        break;
      case 5:
        output.append("D");
        break;
      case 6:
        output.append("DC");
        break;
      case 7:
        output.append("DCC");
        break;
      case 8:
        output.append("DCCC");
        break;
      case 9:
        output.append("CM");
        break;
      default:
        break;
    }

    t = num/10;
    num = num%10;
    switch(t){
      case 1:
        output.append("X");
        break;
      case 2:
        output.append("XX");
        break;
      case 3:
        output.append("XXX");
        break;
      case 4:
        output.append("XL");
        break;
      case 5:
        output.append("L");
        break;
      case 6:
        output.append("LX");
        break;
      case 7:
        output.append("LXX");
        break;
      case 8:
        output.append("LXXX");
        break;
      case 9:
        output.append("XC");
        break;
      default:
        break;
    }

    switch(num){
      case 1:
        output.append("I");
        break;
      case 2:
        output.append("II");
        break;
      case 3:
        output.append("III");
        break;
      case 4:
        output.append("IV");
        break;
      case 5:
        output.append("V");
        break;
      case 6:
        output.append("VI");
        break;
      case 7:
        output.append("VII");
        break;
      case 8:
        output.append("VIII");
        break;
      case 9:
        output.append("IX");
        break;
      default:
        break;
    }

    return output.toString();
  }
}
