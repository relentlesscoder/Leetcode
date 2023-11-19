package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 10/04/2020.
 * #1604 https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/
 */
public class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {

    // time O(n*log(n)), space O(n)
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Integer[] index = new Integer[n];
        for(int i = 0; i < n; i++){
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> keyTime[a].compareTo(keyTime[b]));
        List<String> res = new ArrayList<>();
        if(n < 3){
            return res;
        }
        TreeSet<String> alerts = new TreeSet<>();
        Map<String, int[]> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            String name = keyName[index[i]];
            if(alerts.contains(name)){
                continue;
            }
            int ts = getTimestamp(keyTime[index[i]]);
            map.putIfAbsent(name, new int[]{-1, -1});
            int[] cur = map.get(name);
            if(cur[0] == -1){
                cur[0] = ts;
            }else if(cur[1] == -1){
                if(ts - cur[0] <= 60){
                    cur[1] = ts;
                }else{
                    cur[0] = ts;
                }
            }else{
                if(ts - cur[0] <= 60){
                    alerts.add(name);
                }else if(ts - cur[1] <= 60){
                    cur[0] = cur[1];
                    cur[1] = ts;
                }else{
                    cur[0] = ts;
                    cur[1] = -1;
                }
            }
        }
        res.addAll(alerts);
        return res;
    }

    private int getTimestamp(String timeString){
        String[] vals = timeString.split(":");
        return Integer.parseInt(vals[0]) * 60 + Integer.parseInt(vals[1]);
    }
}
