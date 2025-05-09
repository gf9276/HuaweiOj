package AfterClass.Q1;

//public class Solution {
//    public static String parseIPHeader(String hexString) {
//        // 将十六进制字符串转换为字节数组
//        int len = hexString.length();
//        byte[] data = new byte[len / 2];
//        for (int i = 0; i < len; i += 2) {
//            data[i / 2] = (byte) Integer.parseInt(hexString.substring(i, i + 2), 16);
//        }
//
//        // 提取标识字段（第4到第5字节）
//        int identification = ((data[3] & 0xFF) | (data[2] << 8)) & 0xFFFF;
//
//        // 提取标志字段（第6字节的高3位）
//        int flags = (data[5] >> 5) & 0x07;
//
//        return identification + "," + flags;
//    }
//}

class Solution {
    public String getIpData(String input) {
        // 提取标识字段的两个字节（字符8-11，共4位）
        String identificationHex = input.substring(8, 12);
        // 将其按照大端，转换为int类型
        int identification = Integer.parseInt(identificationHex, 16);

        // 提取标志所在字节（字符12-13，共2位）
        String flagHex = input.substring(12, 14);
        int flagByte = Integer.parseInt(flagHex, 16);
        int flags = (flagByte >> 5) & 0x07; // 右移5位取高3位（只保留高3位）

        return identification + "," + flags;
    }
}