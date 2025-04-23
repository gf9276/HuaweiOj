package oj_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// 13 完美答案收集
/**
 * OJ考题代码：完美答案收集
 *
 * @author 命题组
 * @since 2019-12-11
 */
class Solution {
    int getMinPeople(int questionsCount, int peopleCount, int[][] correctRanges) {
        // 可以大鱼吃小鱼，合并了再说
        Arrays.sort(correctRanges, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        if (correctRanges[correctRanges.length - 1][1] < questionsCount) {
            return -1;
        }
        if (correctRanges[0][0] > 1) {
            return -1;
        }

        List<int[]> mergedCorrectRanges = new ArrayList<>();
        for (int[] correctRange : correctRanges) {
            if (mergedCorrectRanges.isEmpty()) {
                mergedCorrectRanges.add(Arrays.copyOf(correctRange, 2));
                continue;
            }
            int[] lastMergedCorrectRange = mergedCorrectRanges.get(mergedCorrectRanges.size() - 1);
            if (lastMergedCorrectRange[1] + 1 < correctRange[0]) {
                // 完蛋了，合并不了
                return -1;
            }
            if (correctRange[0] <= lastMergedCorrectRange[0]) {
                // 更新你的位置，这种包含关系，计算一个就行了
                lastMergedCorrectRange[1] = correctRange[1];
                continue;
            }
            if (correctRange[1] <= lastMergedCorrectRange[1]) {
                // 存在大套小的包含关系，直接跳过
                continue;
            }
            // 有重叠，但是没有包含，直接加入
            mergedCorrectRanges.add(Arrays.copyOf(correctRange, 2));
            mergedCorrectRanges.get(mergedCorrectRanges.size() - 1)[0] = lastMergedCorrectRange[1] + 1;
        }
        return mergedCorrectRanges.size();
    }
}