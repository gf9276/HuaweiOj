package AfterClass.Q3;

import java.util.*;

class Solution {
    // 注：relations[i] = [id1, id2] id1依赖id2
    int getMinTime(int taskNum, int[][] relations) {
        // 显然这是拓扑排序
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] in = new int[taskNum + 1];
        for (int[] relation : relations) {
            in[relation[0]]++; // 入度++
            if (!map.containsKey(relation[1])) {
                map.put(relation[1], new ArrayList<>());
            }
            map.get(relation[1]).add(relation[0]);
        }
        Queue<Integer> queue = new LinkedList<>(); // 队列，进行拓扑排序
        for (int i = 1; i <= taskNum; i++) {
            if (in[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size(); // 这些可以一次性搞定的
            ans++;
            for (int i = 0; i < size; i++) {
                int curId = queue.poll(); // 拿出当前的点
                if (map.containsKey(curId)) {
                    for (int nextP : map.get(curId)) {
                        in[nextP]--;
                        if (in[nextP] == 0) {
                            queue.add(nextP);
                        }
                    }
                }
            }
        }
        return ans;
    }
}

/*
from typing import List, Tuple
from collections import deque, defaultdict

class Solution:
    def get_min_time(self, task_num: int, relations: List[Tuple[int, int]]) -> int:
        # 构建邻接表和入度数组
        graph = defaultdict(list) # 邻接表
        in_degree = [0] * (task_num + 1)  # 任务编号从1到taskNum

        # 遍历关系，构建邻接表
        for next_id, prev_id in relations:
            in_degree[next_id] += 1
            graph[prev_id].append(next_id)

        # 选取入度为0的点作为起点
        queue = deque()
        for i in range(1, task_num + 1):
            if in_degree[i] == 0:
                queue.append(i)

        time = 0

        while queue:
            level_size = len(queue) # 此处可以并行进行
            time += 1 # 按照一次计算

            for _ in range(level_size):
                cur = queue.popleft() # 当前任务
                for neighbor in graph[cur]:
                    in_degree[neighbor] -= 1 # 当前任务完成，相关任务入度减1
                    if in_degree[neighbor] == 0: # 入度为0，则进入队列，可以作为起点
                        queue.append(neighbor)

        return time
*/