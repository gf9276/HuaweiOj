package AfterClass.Q2;

import java.util.*;

class OrderSystem {
    Map<Integer, Integer> customerGoodsMap; // 记录每个顾客的订货数量
    Map<Integer, Integer> customerStampMap; // 记录数量变化过程的次数（时间戳）
    Map<String, Queue<Integer>> goodCustomerMap; // 按顺序记录顾客订货情况
    PriorityQueue<int[]> heap; // 用于query [顾客id（小），货物数量（大），时间戳]

    OrderSystem() {
        customerGoodsMap = new HashMap<>();
        customerStampMap = new HashMap<>();
        goodCustomerMap = new HashMap<>();
        heap = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
    }

    void order(int customerId, List<String> goods) {
        // 更新顾客的订货数量
        customerGoodsMap.put(customerId, customerGoodsMap.getOrDefault(customerId, 0) + goods.size());
        // 按顺序记录顾客的订货情况
        for (String good : goods) {
            if (!goodCustomerMap.containsKey(good)) {
                goodCustomerMap.put(good, new LinkedList<>());
            }
            goodCustomerMap.get(good).add(customerId);
        }
        // 顾客订货数量更新，包括时间戳和堆栈
        customerStampMap.put(customerId, customerStampMap.getOrDefault(customerId, 0) + 1);
        heap.add(new int[]{customerId, customerGoodsMap.get(customerId), customerStampMap.get(customerId)});
    }

    void deliver(List<String> goods) {
        // 发货，并实时更新货物信息
        for (String good : goods) {
            int customerId = goodCustomerMap.get(good).poll();
            customerGoodsMap.put(customerId, customerGoodsMap.get(customerId) - 1);
            customerStampMap.put(customerId, customerStampMap.get(customerId) + 1);
            heap.add(new int[]{customerId, customerGoodsMap.get(customerId), customerStampMap.get(customerId)});
        }
    }

    int query() {
        while (!heap.isEmpty() && (customerStampMap.get(heap.peek()[0]) != heap.peek()[2] || heap.peek()[1] == 0)) {
            // 已经过时了，这个时间戳（或者货物已经发完了）
            heap.poll();
        }
        return heap.isEmpty() ? -1 : heap.peek()[0];
    }
}