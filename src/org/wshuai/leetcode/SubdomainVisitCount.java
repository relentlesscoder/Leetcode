package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/6/19.
 * #811 https://leetcode.com/problems/subdomain-visit-count/
 */
public class SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        if(cpdomains == null || cpdomains.length == 0){
            return null;
        }
        List<String> res= new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String str: cpdomains){
            String[] arr1 = str.split("\\s+");
            int count = Integer.valueOf(arr1[0]);
            String[] arr2 = arr1[1].split("\\.");
            String cur = "";
            int len = arr2.length;
            for(int i = len-1; i >= 0; i--){
                cur = arr2[i] + (i == len-1 ? "" : ".") + cur;
                map.put(cur, map.getOrDefault(cur, 0) + count);
            }
        }
        map.forEach((k, v) -> {
            res.add("" + v + " " + k);
        });
        return res;
    }
}
