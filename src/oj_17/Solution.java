package oj_17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 17 关键字标识
class Solution {
    String getTaggedString(List<String> words, String inputStr) {
        // 这题逻辑很简单哈，只要找到关键词，合并是随便合并的
        // 问题就在于如何高效的找到关键词
        boolean[] flags = new boolean[inputStr.length()];
        Arrays.fill(flags, false); // 都不存在

        // 当然是kmp算法了，标记关键词
        for (String word : words) {
            List<Integer> curWordBegins = strStr(inputStr, word, getNext(word)); // 所有的匹配项
            for (Integer item : curWordBegins) {
                for (int i = item; i < item + word.length(); i++) {
                    flags[i] = true;
                }
            }
        }

        // 开始合并
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flags.length; ) {
            int nextIdx = i;
            if (flags[i]) {
                // 被标记了
                sb.append("<b>");
                while (nextIdx < flags.length && flags[nextIdx]) {
                    nextIdx++;
                }
                sb.append(inputStr, i, nextIdx);
                sb.append("</b>");
            } else {
                sb.append(inputStr, i, i + 1);
                nextIdx = i + 1;
            }
            i = nextIdx;
        }
        return sb.toString();
    }

    public int[] getNext(String needle) {
        char[] T = needle.toCharArray();
        int jj = 0, k = -1;
        int[] next = new int[needle.length() + 1];
        next[jj] = k;
        while (jj < needle.length()) {
            if (k == -1 || T[jj] == T[k]) {
                jj++;
                k++;
                next[jj] = k;
            } else k = next[k];
        }
        return next;
    }

    public List<Integer> strStr(String haystack, String needle, int[] next) {
        List<Integer> output = new ArrayList<>();
        char[] T = needle.toCharArray();
        char[] S = haystack.toCharArray();


        //然后利用next数组就可使用KMP算法啦
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || S[i] == T[j]) {
                i++;         //继续对下一个字符比较
                j++;         //模式串向右滑动
            } else j = next[j];
            if (j == needle.length()) {
                output.add(i - j);
                j = next[j];
            }
        }
        return output;
    }
}


