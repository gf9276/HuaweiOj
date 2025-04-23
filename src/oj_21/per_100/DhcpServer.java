package oj_21.per_100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class DhcpServer {
    private Map<String, Integer> macToIp;
    private Map<Integer, String> ipToMac;
    private Map<String, Integer> lastAllocated;
    private PriorityQueue<Integer> unusedHeap;
    private PriorityQueue<Integer> releasedHeap;

    DhcpServer() {
        macToIp = new HashMap<>();
        ipToMac = new HashMap<>();
        lastAllocated = new HashMap<>();
        unusedHeap = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            unusedHeap.offer(i);
        }
        releasedHeap = new PriorityQueue<>();
    }

    public String request(String mac) {
        if (macToIp.containsKey(mac)) {
            return numToIp(macToIp.get(mac));
        }

        if (lastAllocated.containsKey(mac)) {
            int x = lastAllocated.get(mac);
            if (!ipToMac.containsKey(x)) {
                macToIp.put(mac, x);
                ipToMac.put(x, mac);
                lastAllocated.put(mac, x);
                return numToIp(x);
            }
        }

        while (!unusedHeap.isEmpty()) {
            Integer x = unusedHeap.poll();
            if (x != null && !ipToMac.containsKey(x)) {
                macToIp.put(mac, x);
                ipToMac.put(x, mac);
                lastAllocated.put(mac, x);
                return numToIp(x);
            }
        }

        while (!releasedHeap.isEmpty()) {
            Integer x = releasedHeap.poll();
            if (x != null && !ipToMac.containsKey(x)) {
                macToIp.put(mac, x);
                ipToMac.put(x, mac);
                lastAllocated.put(mac, x);
                return numToIp(x);
            }
        }

        return "NA";
    }

    public void release(String mac) {
        if (macToIp.containsKey(mac)) {
            int x = macToIp.get(mac);
            macToIp.remove(mac);
            ipToMac.remove(x);
            releasedHeap.offer(x);
            lastAllocated.put(mac, x);
        }
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