package oj_11;

// 11 遥控小车
class Solution {
    // 待实现函数，在此函数中填入答题代码
    String execCommand(String commands) {
        // 简单模拟题
        int curSituation = 0;
        int[][] movePos = new int[4][2];
        movePos[0] = new int[]{0, 1}; // 上北
        movePos[2] = new int[]{0, -1}; // 下南
        movePos[1] = new int[]{-1, 0}; // 左西
        movePos[3] = new int[]{1, 0}; // 右东
        int curX = 0;
        int curY = 0;
        for (char c : commands.toCharArray()) {
            if (c == 'G') {
                // 直接走就行了
                curX += movePos[curSituation][0];
                curY += movePos[curSituation][1];
            } else if (c == 'L') {
                // 左转
                curSituation = (curSituation + 1);
            } else {
                // 右转
                curSituation = (curSituation + 4 - 1) % 4;
            }
        }
        return "(" + curX + "," + curY + ")";
    }
}