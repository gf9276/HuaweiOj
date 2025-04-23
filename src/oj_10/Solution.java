package oj_10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    String characterSort(String inputStr) {
        List<Integer> digsPos = new ArrayList<>();
        List<Integer> charsPos = new ArrayList<>();
        List<Integer> digs = new ArrayList<>();
        List<Integer> chars = new ArrayList<>();
        for (int i = 0; i < inputStr.length(); i++) {
            char c = inputStr.charAt(i);
            if (c >= '0' && c <= '9') {
                digsPos.add(i);
                digs.add((int) c);
            } else {
                charsPos.add(i);
                if (c <= 'Z' && c >= 'A') {
                    chars.add((int) c + 256);
                } else {
                    chars.add((int) c);
                }
            }
        }

        Collections.sort(digs);
        Collections.sort(chars);
        char[] outputChars = new char[inputStr.length()];
        for (int i = 0; i < digs.size(); i++) {
            outputChars[digsPos.get(i)] = (char) (int) digs.get(i);
        }
        for (int i = 0; i < chars.size(); i++) {
            outputChars[charsPos.get(i)] = (char) (int) (chars.get(i) % 256);
        }
        return new String(outputChars);
    }
}
