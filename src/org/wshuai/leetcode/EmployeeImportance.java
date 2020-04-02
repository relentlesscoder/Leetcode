package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 08/19/2019.
 * #0690 https://leetcode.com/problems/employee-importance/
 */
public class EmployeeImportance {
	// time O(n)
	public int getImportance(List<Employee> employees, int id) {
		Map<Integer, Employee> map = new HashMap<>();
		for(Employee emp : employees){
			map.put(emp.id, emp);
		}
		return dfs(id, map);
	}

	private int dfs(int id, Map<Integer, Employee> map){
		Employee cur = map.get(id);
		int res = cur.importance;
		for(int sub : cur.subordinates){
			res += dfs(sub, map);
		}
		return res;
	}
}

class Employee {
	// It's the unique id of each node;
	// unique id of this employee
	public int id;
	// the importance value of this employee
	public int importance;
	// the id of direct subordinates
	public List<Integer> subordinates;
};
