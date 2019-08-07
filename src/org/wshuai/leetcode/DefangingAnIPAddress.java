package org.wshuai.leetcode;

/**
 * Created by Wei on 8/7/19.
 * #1108 https://leetcode.com/problems/defanging-an-ip-address/
 */
public class DefangingAnIPAddress {
    public String defangIPaddr(String address) {
        if(address == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < address.length(); i++){
            char c = address.charAt(i);
            if(c == '.'){
                sb.append("[.]");
            }else{
                sb.append(""+c);
            }
        }
        return sb.toString();
    }
}
