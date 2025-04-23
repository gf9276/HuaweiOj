package oj_12;

import java.util.HashMap;
import java.util.Map;

class Solution {
    int freeOrder(String[] orderTime) {
        // 要考虑毫秒完全一致的情况
        Map<String, int[]> map = new HashMap<>();
        for (String s : orderTime) {
            int time = Integer.parseInt(s.substring(s.length() - 3));
            String key = s.substring(0, s.length() - 4);
            if (!map.containsKey(key)) {
                map.put(key, new int[]{time, 1});
            } else {
                if (map.get(key)[0] > time) {
                    map.put(key, new int[]{time, 1});
                } else if (map.get(key)[0] == time) {
                    map.get(key)[1]++;
                }
            }
        }
        int ans = 0;
        for (String key : map.keySet()) {
            ans += map.get(key)[1];
        }
        return ans;
    }
}