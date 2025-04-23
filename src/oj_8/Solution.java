package oj_8;

// 8 统计无重复字符子串
class Solution {
    int getCountOfSubString(String input) {
        // 看这数据量和返回类型，不用担心爆上限了
        // 右侧多一个，相当于多n个不重复
        int ans = 0;

        int[] cnts = new int[26];
        int begin = 0;
        for (int end = 0; end < input.length(); end++) {
            char curC = input.charAt(end);
            // 清除重复项
            while (cnts[curC - 'a'] == 1) {
                cnts[input.charAt(begin) - 'a'] = 0;
                begin++;
            }
            cnts[curC - 'a'] = 1;
            ans += (end - begin + 1);
        }
        return ans;
    }
}

