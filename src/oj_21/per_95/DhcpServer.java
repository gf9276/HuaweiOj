package oj_21.per_95;

// 准确率95%, 修改后已为100%，见53行

import java.util.*;

class DhcpServer {
    PriorityQueue<Integer> neverUsedQueue; // 完全没有用过的
    PriorityQueue<Integer> freeQueue; // 用过但是释放掉了
    Map<String, Integer> lastUsedMap; // 记录最近用过的IP
    Map<String, Integer> curUsedMap; // 记录当前使用的IP
    boolean[] used; // 记录256个IP的使用情况

    DhcpServer() {
        neverUsedQueue = new PriorityQueue<>(); // 从未使用过的Ip，最小堆，序号小的放在最上面
        freeQueue = new PriorityQueue<>(); // 使用过，但是空闲的Ip，最小堆
        lastUsedMap = new HashMap<>();
        curUsedMap = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            neverUsedQueue.add(i);
        }
        used = new boolean[256]; // 记录256个IP的使用情况
        Arrays.fill(used, false);
    }

    String request(String mac) {
        // 开始分配IP
        if (curUsedMap.containsKey(mac)) {
            // 如果对应的IP已分配并未释放，则为重复申请，直接返回对应已分配的IP地址。
            return numToIp(curUsedMap.get(mac));
        }

        if (lastUsedMap.containsKey(mac) && !used[lastUsedMap.get(mac)]) {
            // 如果一个MAC地址已申请过并已释放，即：当前未分配IP地址，则为再申请，优先分配最近一次曾经为其分配过的IP地址，请返回此地址。
            Integer curIp = lastUsedMap.get(mac);
            curUsedMap.put(mac, curIp);
            used[curIp] = true;
            return numToIp(curIp);
        }

        if (!neverUsedQueue.isEmpty()) {
            // 按升序分配从未被分配过的IP地址；
            Integer curIp = neverUsedQueue.poll();
            curUsedMap.put(mac, curIp);
            used[curIp] = true;
            return numToIp(curIp);
        }

        while (!freeQueue.isEmpty()) {
            // 如果地址池中地址都已被分配过，则按升序分配已释放出来的IP地址
            Integer curIp = freeQueue.poll();
            // 这里必须要判断是否可用，为什么呢？除非多线程冲突了，不然我实在想象不出来，这里为什么需要判断？
            if (!used[curIp]) {
                curUsedMap.put(mac, curIp);
                used[curIp] = true;
                return numToIp(curIp);
            }
        }

        // 若仍然无法分配成功，则返回NA
        return "NA";
    }

    void release(String mac) {
        // 根据输入的MAC地址释放已分配的IP地址，如果申请释放的对应的IP地址已分配，则释放此IP地址；
        if (curUsedMap.containsKey(mac)) {
            Integer curIp = curUsedMap.get(mac);
            used[curIp] = false; // 显示没有使用过
            freeQueue.add(curIp); // 加入空闲Ip列表
            lastUsedMap.put(mac, curIp); // 放到最近一次使用记录中
            curUsedMap.remove(mac); // 移除这个mac地址
        }
        // 如果申请释放的对应的IP地址不存在，则不作任何事情；
    }

    private String numToIp(int x) {
        return "192.168.0." + x;
    }
}

/*
import heapq

class Solution:
    def __init__(self):
        self.mac_to_ip = {}
        self.ip_to_mac = {}
        self.last_allocated = {}
        self.unused_heap = list(range(256))
        heapq.heapify(self.unused_heap)
        self.released_heap = []
        self.used_ips = [False] * 256

    def num_to_ip(self, x):
        return f"192.168.0.{x}"

    def dhcp_server(self, mac_list):
        data_list = list()
        for value in mac_list:
            d_tmp = value.split('=')
            data_list.append({'action': d_tmp[0], 'mac': d_tmp[1]})
        for data in data_list:
            if data['action'] == "REQUEST":
                self.request(data['mac'])
            elif data['action'] == "RELEASE":
                self.release(data['mac'])

    def request(self, mac):
        if mac in self.mac_to_ip:
            print(self.num_to_ip(self.mac_to_ip[mac]))
            return

        if mac in self.last_allocated:
            x = self.last_allocated[mac]
            if x not in self.ip_to_mac:
                self.mac_to_ip[mac] = x
                self.ip_to_mac[x] = mac
                self.last_allocated[mac] = x
                print(self.num_to_ip(x))
                return

        while self.unused_heap:
            x = heapq.heappop(self.unused_heap)
            if x not in self.ip_to_mac:
                self.mac_to_ip[mac] = x
                self.ip_to_mac[x] = mac
                self.used_ips[x] = True
                self.last_allocated[mac] = x
                print(self.num_to_ip(x))
                return

        while self.released_heap:
            x = heapq.heappop(self.released_heap)
            if x not in self.ip_to_mac:
                self.mac_to_ip[mac] = x
                self.ip_to_mac[x] = mac
                self.last_allocated[mac] = x
                print(self.num_to_ip(x))
                return

        print("NA")

    def release(self, mac):
        if mac in self.mac_to_ip:
            x = self.mac_to_ip[mac]
            del self.mac_to_ip[mac]
            del self.ip_to_mac[x]
            heapq.heappush(self.released_heap, x)
            self.last_allocated[mac] = x
*/