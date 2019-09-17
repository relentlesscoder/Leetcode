package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 9/16/19.
 * #428 https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 */
public class SerializeAndDeserializeNaryTree {
    // Encodes a tree to a single string.
    public String serialize(NaryTreeNode root) {
        if(root == null){
            return "";
        }
        if(root.children.size() == 0){
            return "" + root.val;
        }
        StringBuilder res = new StringBuilder();
        res.append("" + root.val + "[");
        for(NaryTreeNode c: root.children){
            res.append(serialize(c) + " ");
        }
        res.replace(res.length()-1, res.length(), "]");
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public NaryTreeNode deserialize(String data) {
        if(data == null || data.isEmpty()){
            return null;
        }
        Map<Integer, List<NaryTreeNode>> map = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int level = 0;
        int i = 0;
        char[] arr = data.toCharArray();
        while(i < arr.length){
            if(Character.isDigit(arr[i])){
                int j = i+1;
                while(j < arr.length && Character.isDigit(arr[j])){
                    j++;
                }
                int val = Integer.parseInt(data.substring(i, j));
                if(!map.containsKey(level)){
                    map.put(level, new ArrayList<>());
                }
                List<NaryTreeNode> lst = map.get(level);
                lst.add(new NaryTreeNode(val, new ArrayList<>()));
                i = j-1;
            }else if(arr[i] == '['){
                level++;
            }else if(arr[i] == ']'){
                List<NaryTreeNode> parents = map.get(level-1);
                NaryTreeNode parent = parents.get(parents.size()-1);
                // nodes at same level (with different parents) cannot add more than once
                // example 1[10[5 0] 3[6]]  nodes 5, 0, 6
                int used = count.getOrDefault(level, 0);
                for(int x = used; x < map.get(level).size(); x++){
                    parent.children.add(map.get(level).get(x));
                    used++;
                }
                count.put(level, used);
                level--;
            }
            i++;
        }
        return map.get(0).get(0);
    }
}
