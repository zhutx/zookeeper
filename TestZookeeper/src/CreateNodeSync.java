import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 同步创建节点
 */
public class CreateNodeSync implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.1.105:2181", 5000, new CreateNodeSync());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void doSomething() {
        try {
            String path = zooKeeper.create("/node_4", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("return path: " + path); // 输出服务端返回的节点路径
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到事件: " + event);
        if (event.getState() == Event.KeeperState.SyncConnected) { // 获取服务端状态
            doSomething();
        }
    }
}
