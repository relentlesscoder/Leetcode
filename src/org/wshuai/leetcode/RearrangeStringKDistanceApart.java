package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 8/26/19.
 * #358 https://leetcode.com/problems/rearrange-string-k-distance-apart/
 */
public class RearrangeStringKDistanceApart {
    public String rearrangeString(String s, int k) {
        if(k == 0){
            return s;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(char c: s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>(){
            public int compare(Character c1, Character c2){
                if(map.get(c1).intValue() != map.get(c2).intValue()){
                    return map.get(c2)-map.get(c1);
                }else{
                    return c1.compareTo(c2);
                }
            }
        });

        for(char c: map.keySet()){
            queue.offer(c);
        }

        StringBuilder sb = new StringBuilder();

        int len = s.length();

        while(!queue.isEmpty()){
            int cnt = Math.min(k, len);
            ArrayList<Character> temp = new ArrayList<>();

            for(int i = 0; i < cnt; i++){
                if(queue.isEmpty()){
                    return "";
                }

                char c = queue.poll();
                sb.append(String.valueOf(c));

                map.put(c, map.get(c)-1);

                if(map.get(c) > 0){
                    temp.add(c);
                }

                len--;
            }

            for(char c: temp){
                queue.offer(c);
            }
        }

        return sb.toString();
    }
}
