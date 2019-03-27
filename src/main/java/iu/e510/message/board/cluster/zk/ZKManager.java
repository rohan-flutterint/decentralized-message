package iu.e510.message.board.cluster.zk;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public interface ZKManager {
    void create(String path, byte[] data, CreateMode mode) throws Exception;

    byte[] getData(String path) throws Exception;

    void set(String path, byte[] data) throws Exception;

    void closeManager() throws InterruptedException;

    List<String> getAllChildren(String path) throws Exception;

    PathChildrenCache getPathChildrenCache(String path);
}
