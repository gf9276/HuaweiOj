package oj_1;

import java.util.*;

class FileSystem {
    String curDir;
    Map<String, Set<String>> curDirFiles;
    Map<String, Set<String>> curDirDirs;

    FileSystem() {
        this.curDir = "/";
        this.curDirFiles = new HashMap<>();
        this.curDirDirs = new HashMap<>();
        this.curDirFiles.put("/", new HashSet<>());
        this.curDirDirs.put("/", new HashSet<>());
    }

    boolean makeDir(String dirName) {
        if (this.curDirFiles.get(this.curDir).contains(dirName)) {
            return false;
        }
        if (this.curDirDirs.get(this.curDir).contains(dirName)) {
            return false;
        }
        this.curDirDirs.get(this.curDir).add(dirName); // 当前路径下添加该文件
        this.curDirFiles.put(this.curDir + dirName + "/", new HashSet<>());
        this.curDirDirs.put(this.curDir + dirName + "/", new HashSet<>());
        return true;
    }

    boolean createFile(String fileName) {
        if (this.curDirFiles.get(this.curDir).contains(fileName)) {
            return false;
        }
        if (this.curDirDirs.get(this.curDir).contains(fileName)) {
            return false;
        }
        this.curDirFiles.get(this.curDir).add(fileName); // 当前路径下添加该文件
        return true;
    }

    boolean changeDir(String pathName) {
        if (pathName.equals("/")) {
            // 直接返回根目录
            curDir = "/";
            return true;
        }
        if (pathName.isEmpty()) {
            // pathName = ""，不改变
            return true;
        }
        if (pathName.endsWith("/")) {
            // 以 / 为结尾，为了确保后面的统一，把 / 删除
            pathName = pathName.substring(0, pathName.length() - 1);
        }
        if (pathName.charAt(0) != '/') {
            // 不以 / 开始，是相对路径
            pathName = curDir + pathName + "/";
        } else {
            // 是绝对路径，在最后面加上 / 表示这是一个文件夹就行
            pathName += "/";
        }
        String[] paths = pathName.split("/");
//        System.out.println(paths.length);
        paths[0] = "/";
        boolean contains = true;
        String curPath = "";
        for (int i = 0; i < paths.length - 1; i++) {
            curPath = curPath + paths[i];
            if (curPath.charAt(curPath.length() - 1) != '/') {
                curPath += "/";
            }
//            System.out.println(curPath);
            if (!curDirDirs.get(curPath).contains(paths[i + 1])) {
                contains = false;
                break;
            }
        }
        if (contains) {
            curDir = pathName;
            return true;
        }
        return false;
    }

    boolean remove(String name) {
        if (curDirDirs.get(curDir).contains(name)) {
            // 删除目录
            curDirDirs.get(curDir).remove(name);
            curDirDirs.remove(curDir + name + "/");
            curDirFiles.remove(curDir + name + "/");
            return true;
        }
        if (curDirFiles.get(curDir).contains(name)) {
            // 删除文件
            curDirFiles.get(curDir).remove(name);
            return true;
        }
        return false;
    }

    List<String> listDir() {
        if (!curDirDirs.containsKey(curDir)) {
            System.out.println(curDir);
        }
        List<String> output1 = new ArrayList<>(curDirDirs.get(curDir));
        Collections.sort(output1);
        List<String> output2 = new ArrayList<>(curDirFiles.get(curDir));
        Collections.sort(output2);
        output1.addAll(output2);
        return output1;
    }
}
