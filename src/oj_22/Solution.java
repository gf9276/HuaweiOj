package oj_22;

class Solution {
    // 待实现函数，在此函数中填入答题代码
    int getLongestNum(long num) {
        // 在此补充你的代码
        int bitNbr = 64 - Long.numberOfLeadingZeros(num);

        int maxOneNbr = 0;
        int maxZeroNbr = 0;
        int curOneNbr = 0;
        int curZeroNbr = 0;

        for (int i = 0; i <= bitNbr; i++) {
            if (((num >> i) & 1) == 1) {
                curZeroNbr = 0;
                curOneNbr++;
                maxOneNbr = Math.max(curOneNbr, maxOneNbr);
            } else {
                curOneNbr = 0;
                curZeroNbr++;
                maxZeroNbr = Math.max(curZeroNbr, maxZeroNbr);
            }
        }
        return Math.max(maxOneNbr, maxZeroNbr);
    }
}
