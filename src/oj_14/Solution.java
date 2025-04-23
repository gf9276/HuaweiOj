package oj_14;

import java.util.*;

class Solution {
    String logAnonymize(String[] keys, String log) {
        // 纯模拟题啊
        keys = Arrays.stream(keys).map(String::toUpperCase).toArray(String[]::new); // 大写方便比较
        Set<String> keySets = new HashSet<>(Arrays.asList(keys)); // 集合方便比较

        // 按照逗号划分每条信息
        String[] pairs = log.split(",");

        // 开始屏蔽
        for (int i = 0; i < pairs.length; i++) {
            String[] parts = pairs[i].split(":");
            String key = parts[0];
            StringBuilder value = new StringBuilder(parts[1]);
            if (key.equalsIgnoreCase("password") || key.equalsIgnoreCase("pwd")) {
                // key为password或pwd的：对应的 value 统一用6个*替代。
                value = new StringBuilder("******");
            } else if (keySets.contains(key.toUpperCase()) && key.toUpperCase().endsWith("IP")) {
                // key在words中，且以IP（不区分大小写）结尾的：对应value中ip地址中间两段分别用3个* 替代（不用考虑IPv6格式），用例保证IP格式的合法性。
                parts = value.toString().split("\\.");
                value = new StringBuilder(parts[0] + ".***.***." + parts[3]);
            } else if (keySets.contains(key.toUpperCase())) {
                // key在words中，且以非IP结尾的：仅需对value中最右侧的长度（L）大于等于4，且连续为数字的子串进行屏蔽，
                // 从倒数第L/4+1个数字字符开始（比如L为9，从倒数第3个开始），从右到左对中间的L/2个字符用*替代。
                // 判断最右侧是否均为数字
                value = new StringBuilder(maskRule3(String.valueOf(value)));
            }
            pairs[i] = key + ":" + value;
        }
        return String.join(",", pairs);
    }

    private String maskRule3(String value) {
        List<int[]> candidates = new ArrayList<>(); // 保存连续的数字字符串

        // 记录所有满足条件的纯数字子串
        int begin = 0;  // 起点
        int end = 0; // 终点
        while (end < value.length()) {
            // 如果是数字，就一直挪动，直到非数字
            while (end < value.length() && value.charAt(end) <= '9' && value.charAt(end) >= '0') {
                end++;
            }
            // 此时end指向非数字字符，判断长度是否大于等于4,满足条件则加入
            if (end - begin >= 4) {
                candidates.add(new int[]{begin, end - 1});
            }
            // 如果end == begin，说明当前就是非数字，因此end没有动过，此时需要手动移动
            if (end == begin) {
                end++;
            }
            // 重置起点，准备开始下一轮判断
            begin = end;
        }

        // 没有需要遮掩的候选人
        if (candidates.isEmpty()) {
            return value;
        }

        int[] lastSubStr = candidates.get(candidates.size() - 1); // 最后一个符合条件的子串
        int subStrStart = lastSubStr[0]; // 子串起点
        int subStrEnd = lastSubStr[1]; // 子串终点
        int L = subStrEnd - subStrStart + 1; // 子串长度

        int endInSub = L - (L / 4 + 1); // 子串中的终点（倒数第(L / 4 + 1)字符的索引）
        int nbrReplace = L / 2; // 要遮掩的长度
        int startInSub = endInSub - (nbrReplace - 1); // 子串中的起点索引

        int globalStart = subStrStart + startInSub; // 全局起点
        int globalEnd = subStrStart + endInSub; // 全局终点

        char[] chars = value.toCharArray();
        for (int i = globalStart; i <= globalEnd; i++) {
            chars[i] = '*';
        }

        return String.valueOf(chars);
    }
}
