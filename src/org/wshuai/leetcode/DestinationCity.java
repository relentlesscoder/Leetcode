package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Wei on 05/03/2020.
 * #1436 https://leetcode.com/problems/destination-city/
 */
public class DestinationCity {
    // time O(n), space O(n)
    public String destCity(List<List<String>> paths) {
        Set<String> dest = new HashSet<>(), from = new HashSet<>();
        for(List<String> p : paths){
            dest.add(p.get(1));
            from.add(p.get(0));
        }
        for(String d : dest){
            if(!from.contains(d)){
                return d;
            }
        }
        return "";
    }
}
