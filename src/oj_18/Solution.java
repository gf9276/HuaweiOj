package oj_18;


//  18 简易文本编辑器

/**
 * OJ考题代码：简易文本编辑器
 *
 * @author 命题组
 * @since 2020-3-3
 */
//class Solution {
//    int getMinStep(int[] steps) {
//        int ans = 0;
//
//        Queue<Integer[]> queue = new LinkedList<>();
//        for (int i = 0; i < steps.length; i++) {
//            if (steps[i] >= 1) {
//                queue.add(new Integer[]{i, steps[i]});
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            Queue<Integer[]> tmpQ = new LinkedList<>();
//            tmpQ.add(queue.poll());
//            while (!queue.isEmpty() && (tmpQ.peek()[0] + tmpQ.size() == queue.peek()[0])) {
//                tmpQ.add(queue.poll());
//            }
//            ans++;
//            while (!tmpQ.isEmpty()) {
//                Integer[] cur = tmpQ.poll();
//                cur[1]--;
//                if (cur[1] > 0) {
//                    queue.add(cur);
//                }
//            }
//        }
//        return ans;
//    }
//}

class Solution {
    int getMinStep(int[] steps) {
        // 或许可以换个思路
        int ans = 0;
        for (int i = 0; i < steps.length; i++) {
            if (i + 1 == steps.length) {
                ans += steps[i];
            } else {
                ans += Math.max(0, steps[i] - steps[i + 1]);
            }
        }
        return ans;
    }
}

