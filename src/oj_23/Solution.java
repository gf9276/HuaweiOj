package oj_23;

// 24 批次计算任务

import java.util.HashMap;
import java.util.Map;

/**
 * OJ考题代码：批次计算任务
 *
 * @author 命题组
 * @since 2020-05-23
 */
class Solution {
    // 待实现函数，在此函数中填入答题代码
    int[] getActiveUserNum(String[] logs) {
        int[] output = new int[32];

        Map<String, Integer>[] maps = new HashMap[31]; // 31天
        Map<String, Integer> monthMap = new HashMap<>();
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new HashMap<>();
        }

        for (String log : logs) {
            String[] strs = log.split("\\|");
            String date = strs[0];
            int days = Integer.parseInt(date.split("-")[2]);
            String ip = strs[1];
            // ip 规整化，消除前导0
            String[] ipSplits = ip.split("\\.");
            for (int i = 0; i < ipSplits.length; i++) {
                ipSplits[i] = ipSplits[i].replaceFirst("^0+", "");
            }
            ip = "";
            for (int i = 0; i < ipSplits.length; i++) {
                ip += ipSplits[i];
                if (i != ipSplits.length - 1) {
                    ip += ".";
                }
            }

            if (strs[2].equals("/login.do") && strs[3].equals("success")) {
                monthMap.put(ip, monthMap.getOrDefault(ip, 0) + 1);
                maps[days - 1].put(ip, maps[days - 1].getOrDefault(ip, 0) + 1);
            }
        }

        for (int i = 0; i < maps.length; i++) {
            output[i + 1] = maps[i].size();
        }
        output[0] = monthMap.size();
        return output;
    }
}


