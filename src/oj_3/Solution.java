package oj_3;

/**
 * OJ考题代码：大小端整数
 *
 * @author 命题组
 * @since 2021-02-10
 */
class Solution {
    // 待实现函数，在此函数中填入答题代码
    String getHexString(String num) {
        byte[] bigEndian = new byte[4];
        byte[] littleEndian = new byte[4];

        long intValue;

        // 解析输入并进行范围检查
        try {
            intValue = Long.parseLong(num);
        } catch (NumberFormatException e) {
            return "overflow";
        }

        // 检查范围
        if (intValue < Integer.MIN_VALUE || intValue >= (1L << 32)) {
            return "overflow";
        }

        // 负数以补码形式表示
        if (intValue < 0) {
            intValue += (1L << 32); // 转换为补码
        }

        // 大端序转换
        bigEndian[0] = (byte) (intValue >> 24); // 最高字节
        bigEndian[1] = (byte) (intValue >> 16); // 次高字节
        bigEndian[2] = (byte) (intValue >> 8);  // 次低字节
        bigEndian[3] = (byte) intValue;          // 最低字节

        // 小端序转换
        littleEndian[0] = (byte) intValue;        // 最低字节
        littleEndian[1] = (byte) (intValue >> 8); // 次低字节
        littleEndian[2] = (byte) (intValue >> 16); // 次高字节
        littleEndian[3] = (byte) (intValue >> 24); // 最高字节

        // 大端序输出
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            res.append(String.format("%02X", bigEndian[i] & 0xFF));
            if (i != 3) {
                res.append(" ");
            }
        }

        res.append("\n");

        // 小端序输出
        for (int i = 0; i < 4; i++) {
            res.append(String.format("%02X", littleEndian[i] & 0xFF));
            if (i != 3) {
                res.append(" ");
            }
        }

        return res.toString(); // 去除最后的空格
    }
}

