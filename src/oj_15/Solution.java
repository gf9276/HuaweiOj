package oj_15;

import java.util.*;

class Solution {
    public int[] highestLoadServers(int serverNum, int[][] messages) {
        // message = [start, load]
        // 关键在于TreeSet啊，避免了时间复杂度过高
        Arrays.sort(messages, Comparator.comparingInt(m -> m[0])); // 按照信息的抵达时间排序
        int[] allServerLoads = new int[serverNum]; // 用于记录所有服务器的负载时间
        long[] availableTime = new long[serverNum]; // 用于记录所有服务器的可以使用的开始时间，初始是0是1无所谓
        PriorityQueue<long[]> busy = new PriorityQueue<>(Comparator.comparingLong(a -> a[0])); // 最小堆，记录忙碌服务器信息[工作结束时间，服务器id]
        TreeSet<Integer> availableServers = new TreeSet<>(); // 记录空闲服务器，红黑树可以直接找到符合条件的id，不用自己实现二分

        // 初始化，所有服务器均为空闲状态
        for (int i = 0; i < serverNum; i++) {
            availableServers.add(i);
        }

        // 记录下一台服务器
        int nextServer = 0;

        for (int[] msg : messages) {
            int start = msg[0]; // 开始时间
            int load = msg[1]; // 负载

            // 把所有的空闲服务器都弹出来
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                long[] server = busy.poll();
                int serverId = (int) server[1];
                availableServers.add(serverId);
            }

            // 找到恰好比当前id大的服务器，如果没有，则有两种可能
            // 可能1，一次循环过了，要进行下一个循环，所有直接从空闲服务器里取最小的出来
            // 可能2，单纯没有空位了，这种不用管，候选人直接就是null，直接pass
            Integer candidate = availableServers.ceiling(nextServer);
            if (candidate == null && !availableServers.isEmpty()) {
                candidate = availableServers.first();
            }

            // 若候选人不为空，说明服务器有空闲
            if (candidate != null) {
                allServerLoads[candidate] += load; // 更新负载情况
                availableTime[candidate] = start + load; // 更新下一个可用时间，即结束时间+1，即start+load
                busy.offer(new long[]{availableTime[candidate], candidate}); // 将忙碌服务器加入最小堆里
                availableServers.remove(candidate); // 并将其从可用服务器中删除
                nextServer = (candidate + 1) % serverNum; // 更新下一个待选服务器Id
            }
        }

        // 接下来统计答案即可，取负载最大值
        int maxLoad = Arrays.stream(allServerLoads).max().getAsInt();

        // 提取等于最大值的服务器id，并排序
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < serverNum; i++) {
            if (allServerLoads[i] == maxLoad) {
                resultList.add(i + 1);
            }
        }
        Collections.sort(resultList);

        // 转成数组输出
        int[] res = new int[resultList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resultList.get(i);
        }

        return res;
    }
}