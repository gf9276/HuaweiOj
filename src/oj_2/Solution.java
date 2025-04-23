package oj_2;


import java.util.*;

/**
 * OJ考题代码：单板告警统计
 *
 * @author 命题组
 * @since 2021-01-30
 */
class Solution {
    // 待实现函数，在此函数中填入答题代码
    String[] getAllFault(String[] arrayA, String[] arrayB) {
        //输出arrayA和arrayB的并集，去重后按照16进制由小到大输出
        Set<String> idSet = new TreeSet<>();
        idSet.addAll(Arrays.asList(arrayA));
        idSet.addAll(Arrays.asList(arrayB));

        //将set转为List后再次转为数组
        List<String> tempList = new ArrayList<>(idSet);
        String[] res = new String[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            res[i] = tempList.get(i);
        }
        return res;
    }
}
