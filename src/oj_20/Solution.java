package oj_20;

import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    int taskScheduler(int resourcesNum, int[][] taskAttributes) {
        // 先看优先级，数字小的在前，再看时间，长的在前
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->
        {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        Collections.addAll(heap, taskAttributes);

        PriorityQueue<Long> resourceHeap = new PriorityQueue<>();
        for (int i = 0; i < resourcesNum; i++) {
            resourceHeap.add(0L);
        }
        while (!heap.isEmpty()) {
            resourceHeap.add(resourceHeap.poll() + heap.poll()[0]);
        }
        while (resourceHeap.size() > 1) {
            resourceHeap.poll();
        }
        return (int) (resourceHeap.poll() % 1000000009);
    }
}