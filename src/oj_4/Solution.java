package oj_4;

/**
 * OJ考题代码：二进制转十进制
 *
 * @author 命题组
 * @since 2021-01-12
 */
class Solution {
    // 待实现函数，在此函数中填入答题代码    
    int binaryToDecimal(String binaryString) {
        int res = 0;
        int strLen = binaryString.length();
        for (int i = 0; i < strLen; i++) {
            int num = binaryString.charAt(i) - '0';
            res = res * 2 + num;
        }
        return res;
    }
}


