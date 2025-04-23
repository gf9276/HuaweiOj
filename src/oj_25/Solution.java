package oj_25;

class Solution {
    // 待实现函数，在此函数中填入答题代码
    long getMinArea(int stopPoint, int[][] operations) {
        int curY = 0;
        int curX = 0;
        long ans = 0L;
        for (int i = 0; i < operations.length; i++) {
            int nextX = operations[i][0];
            int nextY = curY + operations[i][1];
            ans += (long) (nextX - curX) * Math.abs(curY);
            curY = nextY;
            curX = nextX;
        }
        ans += (long) (stopPoint - curX) * Math.abs(curY);
        return ans;
    }
}
