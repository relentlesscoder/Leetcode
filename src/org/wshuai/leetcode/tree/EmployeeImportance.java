package org.wshuai.leetcode.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/19/2019.
 * #0690 https://leetcode.com/problems/employee-importance/
 */
public class EmployeeImportance {

    // time O(n), space O(n)
    public int getImportance(List<Employee> employees, int id) {
        int n = employees.size();
        // 哈希表存员工 id 到在列表中的索引的映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Employee emp = employees.get(i);
            map.put(emp.id, i);
        }
        // DFS 找到重要性的总和
        return dfs(employees, id, map);
    }

    private int dfs(List<Employee> employees, int id, Map<Integer, Integer> map) {
        Employee emp = employees.get(map.get(id));
        int res = emp.importance;
        for (int next : emp.subordinates) {
            res += dfs(employees, next, map);
        }
        return res;
    }

    /**
     * Definition for Employee.
     */
    private class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }
}
