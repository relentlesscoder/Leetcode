package org.wshuai.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Wei on 8/7/19.
 * #1086 https://leetcode.com/problems/high-five/
 */
public class HighFive {
    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (i1, i2)-> i1[0] == i2[0] ? i2[1]-i1[1] : i1[0]-i2[0]);

        List<int[]> lst = new ArrayList<int[]>();
        int id = items[0][0];
        int sum = 0;
        int count = 0;
        for(int i = 0; i < items.length; i++){
            int currId = items[i][0];
            if(count < 5){
                sum += items[i][1];
                count++;
            }else if(id == currId){
                continue;
            }else{
                int[] arr = new int[2];
                arr[0] = id;
                arr[1] = sum/5;
                lst.add(arr);

                id = currId;
                sum = items[i][1];
                count = 1;
            }
        }
        int[] arr1 = new int[2];
        arr1[0] = id;
        arr1[1] = sum/5;
        lst.add(arr1);

        int[][] res = new int[lst.size()][2];
        for(int i = 0; i < lst.size(); i++){
            res[i] = lst.get(i);
        }
        return res;
    }
}
