package org.wshuai.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wei on 12/02/2023.
 * #2408 https://leetcode.com/problems/design-sql/
 */
public class DesignSQL {

    private class SQL {

        private Map<String, Integer> tableIds;
        private Map<String, Map<Integer, String[]>> tables;

        // time O(n), space O(n)
        public SQL(List<String> names, List<Integer> columns) {
            tableIds = new HashMap<>();
            tables = new HashMap<>();
            for (String name : names) {
                tableIds.put(name, 1);
                tables.put(name, new HashMap<>());
            }
        }

        // time O(1)
        public void insertRow(String name, List<String> row) {
            int nextId = tableIds.get(name);
            tableIds.put(name, nextId + 1);
            tables.get(name).put(nextId, row.toArray(new String[0]));
        }

        // time O(1)
        public void deleteRow(String name, int rowId) {
            tables.get(name).remove(rowId);
        }

        // time O(1)
        public String selectCell(String name, int rowId, int columnId) {
            return tables.get(name).get(rowId)[columnId - 1];
        }
    }

/**
 * Your SQL object will be instantiated and called as such:
 * SQL obj = new SQL(names, columns);
 * obj.insertRow(name,row);
 * obj.deleteRow(name,rowId);
 * String param_3 = obj.selectCell(name,rowId,columnId);
 */
}
