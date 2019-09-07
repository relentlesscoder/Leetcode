package org.wshuai.leetcode;

/**
 * Created by Wei on 9/6/19.
 * #1071 https://leetcode.com/problems/greatest-common-divisor-of-strings/
 */
public class GreatestCommonDivisorOfStrings {

    // same idea as calculating greatest common divisor
    public String gcdOfStrings(String str1, String str2) {
        if(str1.equals(str2)){
            return str1;
        }
        String max = str1.length() >= str2.length() ? str1 : str2;
        String min = str1.length() < str2.length() ? str1 : str2;
        if(!max.contains(min)){
            return "";
        }
        while(!min.isEmpty()){
            String rem = max.replace(min, "");
            max = rem.length() > min.length() ? rem : min;
            min = rem.length() < min.length() ? rem : min;
        }
        return max;
    }
}
