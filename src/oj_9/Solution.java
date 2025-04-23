package oj_9;

import java.util.ArrayList;
import java.util.List;

// 9 公共字符
class Solution {
    // 无聊的题目
    char[] getNTimesCharacter(int nValue, String[] strings) {
        int[] moreThanNCnts = new int[256]; // 统计超过nValue次的数量

        for (String str : strings) {
            int[] curCCnts = new int[256];
            for (char c : str.toCharArray()) {
                curCCnts[c]++;
                if (curCCnts[c] == nValue) {
                    moreThanNCnts[c]++;
                }
            }
        }

        List<Character> ans = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            if (moreThanNCnts[i] == strings.length) {
                ans.add((char) i);
            }
        }

        char[] output = new char[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            char c = ans.get(i);
            output[i] = c;
        }

        return output;
    }
}



