package oj_16;


// 16 简易校验和算法
class Solution {
    String simpleCheckSum(String inputStr) {
        // 有手就行
        // 先填充，一个字节占两位
        StringBuilder inputStrBuilder = new StringBuilder(inputStr);
        while (inputStrBuilder.length() % 8 != 0) {
            inputStrBuilder.append("FF"); // 0xff对应255
        }
        inputStr = inputStrBuilder.toString();

        int n = inputStr.length() / 8;
        if (n == 1) {
            return inputStr;
        }

        long curNbr = 0L;
        for (int i = 0; i < n; i++) {
            String curHex = inputStr.substring(8 * i, 8 * i + 8);
            curNbr ^= Long.parseLong(curHex, 16);
        }
        StringBuilder ans = new StringBuilder(Long.toHexString(curNbr).toUpperCase());
        // 如果不足8个字符，补全
        while (ans.length() < 8) {
            ans.insert(0, "0");
        }
        return ans.toString();
    }
}