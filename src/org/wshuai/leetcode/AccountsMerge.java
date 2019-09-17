package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 9/17/19.
 * #721 https://leetcode.com/problems/accounts-merge/
 */
public class AccountsMerge {
    private List<List<String>> accounts;
    private int len;
    private int[][] adj;
    private int[] used;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        this.accounts = accounts;
        len = accounts.size();
        adj = new int[len][len];
        used = new int[len];
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++){
            List<String> lst = accounts.get(i);
            for(int j = 1; j < lst.size(); j++){
                if(map.containsKey(lst.get(j))){
                    int v = map.get(lst.get(j));
                    if(v != i){
                        adj[v][i] = adj[i][v] = 1;
                    }
                }
                map.put(lst.get(j), i);
            }
        }
        for(int i = 0; i < len; i++){
            if(used[i] != 1){
                TreeSet<String> set = new TreeSet<>();
                dfs(i, set);
                List<String> details = new ArrayList<>();
                details.add(accounts.get(i).get(0));
                details.addAll(set);
                res.add(details);
            }
        }
        return res;
    }

    private void dfs(int id, TreeSet<String> set){
        used[id] = 1;
        List<String> emails = accounts.get(id);
        for(int i = 1; i < emails.size(); i++){
            set.add(emails.get(i));
        }
        for(int j = 0; j < len; j++){
            if(j != id && adj[id][j] == 1 && used[j] != 1){
                dfs(j, set);
            }
        }
    }
}
