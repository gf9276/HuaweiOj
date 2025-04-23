package oj_7;

import java.util.PriorityQueue;

class Solution {
    // 待实现函数，在此函数中填入答题代码
    int getMaxSendNumber(int cap, int[] billLen, int[] billPrior) {
        // 入参分别表示承载规格（发送花旦总容量的阈值），待发送话单的长度，待发送话单的优先级
        // 解题思路：优先队列，首先按照优先级排序，然后按照待发送长度排序
        // 最多可以发送话单：先发相同优先级下长度更小的好
        PriorityQueue<int[]> queue = new PriorityQueue<>((bill1, bill2) -> bill1[0] != bill2[0] ? bill1[0] - bill2[0] : bill1[1] - bill2[1]);
        for (int i = 0; i < billLen.length; i++) {
            queue.add(new int[]{billPrior[i], billLen[i]});
        }

        int res = 0;
        while (!queue.isEmpty() && cap > 0) {
            int tempLen = queue.poll()[1];
            if (cap >= tempLen) {
                cap -= tempLen;
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}