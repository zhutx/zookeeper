import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOError;
import java.io.IOException;

/**
 * 连接服务端
 */
public class CreateSession implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.1.105:2181", 5000, new CreateSession());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void doSomething() {

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到事件: " + event);
        if (event.getState() == Event.KeeperState.SyncConnected) { // 获取服务端状态

            doSomething();
        }
    }
}
