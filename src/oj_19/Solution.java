package oj_19;

import java.util.HashSet;
import java.util.Set;

// 1 公共字符
class Solution {
    // 待实现函数，在此函数中填入答题代码
    int getLongestFlawedVowelSubstrLen(int flaw, String input) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        int ans = 0;
        // 一定要是flaw
        int begin = 0;
        int end = 0;
        int curFlaw = 0;
        while (begin < input.length() && !set.contains(input.charAt(begin))) {
            begin++;
        }
        if (begin == input.length()) {
            return 0;
        }
        end = begin;
        for (; end < input.length(); end++) {
            if (!set.contains(input.charAt(end))) {
                curFlaw++;
            }
            while (curFlaw > flaw) {
                begin++;
                while (begin < input.length() && !set.contains(input.charAt(begin))) {
                    curFlaw--;
                    begin++;
                }
            }
            if (set.contains(input.charAt(end)) && curFlaw == flaw) {
                ans = Math.max(ans, end - begin + 1);
            }
        }
        return ans;
    }
}




