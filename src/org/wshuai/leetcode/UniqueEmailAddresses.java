package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wei on 4/19/19.
 * #929 https://leetcode.com/problems/unique-email-addresses/
 */
public class UniqueEmailAddresses {
    public int numUniqueEmails(String[] emails) {
        int count = 0;
        Set<String> emailList = new HashSet<String>();
        for(String email : emails){
            String[] arr1 = email.split("\\@", 2);
            String[] arr2 = arr1[0].split("\\+", 2);
            String processed = arr2[0].replaceAll("\\.", "") + "@" + arr1[1];
            if(!emailList.contains(processed)){
                count++;
                emailList.add(processed);
            }
        }
        return count;
    }
}
