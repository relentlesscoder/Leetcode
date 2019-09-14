package org.wshuai.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Wei on 9/13/2019.
 * #433 https://leetcode.com/problems/minimum-genetic-mutation/
 */
public class MinimumGeneticMutation {
    // BFS
    public int minMutation(String start, String end, String[] bank) {
        int level = 0;
        Set<String> set = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for(String g: bank){
            set.add(g);
        }
        char[] arr = new char[]{'A', 'C', 'G', 'T'};
        LinkedList<String> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);
        while(!queue.isEmpty()){
            // use size to record number of nodes on the current level
            int size = queue.size();
            while(size > 0){
                String val = queue.poll();
                if(val.equals(end)){
                    return level;
                }
                char[] curr = val.toCharArray();
                for(int i = 0; i < curr.length; i++){
                    char c = curr[i];
                    for(int j = 0; j < 4; j++){
                        curr[i] = arr[j];
                        String per = new String(curr);
                        if(set.contains(per) && !visited.contains(per)){
                            visited.add(per);
                            queue.offer(per);
                        }
                        curr[i] = c;
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }
}
