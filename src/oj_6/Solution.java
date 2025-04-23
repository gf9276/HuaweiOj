package oj_6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RegCallOperate {
    int type = -1;
    String number = null;

    RegCallOperate(int type, String number) {
        this.type = type;
        this.number = number;
    }
}

class Solution {
    // 好像不难
    String calling(String status, List<RegCallOperate> regCallForwardNums) {
        // 每种状态以最后一次为准
        Map<Integer, RegCallOperate> map = new HashMap<>();
        for (RegCallOperate curTegCallForwardNum : regCallForwardNums) {
            map.put(curTegCallForwardNum.type, curTegCallForwardNum);
        }
        if (map.containsKey(0)) {
            return map.get(0).number;
        }
        if (map.containsKey(1)) {
            if (status.equals("busy")) {
                return map.get(1).number;
            }
        }
        if (map.containsKey(2)) {
            if (status.equals("no-response")) {
                return map.get(2).number;
            }
        }
        if (map.containsKey(3)) {
            if (status.equals("unreachable")) {
                return map.get(3).number;
            }
        }
        if (map.containsKey(4)) {
            if (!status.equals("idle")) {
                return map.get(4).number;
            }
        }
        if (status.equals("idle")) {
            return "success";
        }
        return "failure";
    }
}