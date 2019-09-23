package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 8/19/19.
 * #690 https://leetcode.com/problems/employee-importance/
 */
public class EmployeeImportance {
	public int getImportance(List<Employee> employees, int id) {
		Map<Integer, Employee> map = new HashMap<>();
		int[] imp = new int[1];
		for (Employee emp : employees) {
			map.put(emp.id, emp);
		}
		for (Employee emp : employees) {
			if (emp.id == id) {
				dfs(emp, map, imp);
			}
		}
		return imp[0];
	}

	private void dfs(Employee emp, Map<Integer, Employee> map, int[] imp) {
		imp[0] += emp.importance;
		if (emp.subordinates.size() == 0) {
			return;
		}
		for (int sub : emp.subordinates) {
			dfs(map.get(sub), map, imp);
		}
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
