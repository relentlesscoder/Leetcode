package org.wshuai.leetcode;

import java.util.*;

/**
 * Created by Wei on 12/24/2023.
 * #2590 https://leetcode.com/problems/design-a-todo-list/
 */
public class DesignATodoList {

    // time O(n * log(n)), space O(n)
    private class TodoList {

        private int nextId;
        private Map<Integer, Set<Integer>> userIdToTasks;
        private Map<Integer, TreeSet<Integer>> userIdToDueDate;
        private Map<Integer, Map<String, TreeSet<Integer>>> userIdToTags;
        private Map<Integer, Set<String>> dueDateToTags;
        private Map<Integer, String> taskToDesc;
        private Map<Integer, Integer> taskToDueDate;
        private Map<Integer, Integer> dueDateToTask;

        public TodoList() {
            this.nextId = 1;
            this.userIdToTasks = new HashMap<>();
            this.userIdToDueDate = new HashMap<>();
            this.userIdToTags = new HashMap<>();
            this.taskToDesc = new HashMap<>();
            this.taskToDueDate = new HashMap<>();
            this.dueDateToTask = new HashMap<>();
            this.dueDateToTags = new HashMap<>();
        }

        public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
            int taskId = nextId++;
            userIdToTasks.putIfAbsent(userId, new HashSet<>());
            userIdToTasks.get(userId).add(taskId);
            userIdToDueDate.putIfAbsent(userId, new TreeSet<>());
            userIdToDueDate.get(userId).add(dueDate);
            userIdToTags.putIfAbsent(userId, new HashMap<>());
            for (String tag : tags) {
                userIdToTags.get(userId).putIfAbsent(tag, new TreeSet<>());
                userIdToTags.get(userId).get(tag).add(dueDate);
            }
            taskToDesc.put(taskId, taskDescription);
            taskToDueDate.put(taskId, dueDate);
            dueDateToTask.put(dueDate, taskId);
            dueDateToTags.put(dueDate, new HashSet(tags));
            return taskId;
        }

        public List<String> getAllTasks(int userId) {
            List<String> res = new ArrayList<>();
            if (userIdToDueDate.containsKey(userId)) {
                for (int date : userIdToDueDate.get(userId)) {
                    res.add(taskToDesc.get(dueDateToTask.get(date)));
                }
            }
            return res;
        }

        public List<String> getTasksForTag(int userId, String tag) {
            List<String> res = new ArrayList<>();
            if (userIdToTags.containsKey(userId) && userIdToTags.get(userId).containsKey(tag)) {
                for (int date : userIdToTags.get(userId).get(tag)) {
                    res.add(taskToDesc.get(dueDateToTask.get(date)));
                }
            }
            return res;
        }

        public void completeTask(int userId, int taskId) {
            if (userIdToTasks.containsKey(userId) && userIdToTasks.get(userId).contains(taskId)) {
                userIdToTasks.get(userId).remove(taskId);
                taskToDesc.remove(taskId);
                int dueDate = taskToDueDate.get(taskId);
                taskToDueDate.remove(taskId);
                dueDateToTask.remove(dueDate);
                userIdToDueDate.get(userId).remove(dueDate);
                for (String tag : dueDateToTags.get(dueDate)) {
                    userIdToTags.get(userId).get(tag).remove(dueDate);
                }
                dueDateToTags.remove(dueDate);
            }
        }
    }

/**
 * Your TodoList object will be instantiated and called as such:
 * TodoList obj = new TodoList();
 * int param_1 = obj.addTask(userId,taskDescription,dueDate,tags);
 * List<String> param_2 = obj.getAllTasks(userId);
 * List<String> param_3 = obj.getTasksForTag(userId,tag);
 * obj.completeTask(userId,taskId);
 */
}
