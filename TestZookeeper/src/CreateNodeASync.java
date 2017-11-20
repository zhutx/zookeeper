import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 异步创建节点
 */
public class CreateNodeASync implements Watcher {

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.1.105:2181", 5000, new CreateNodeASync());
        System.out.println(zooKeeper.getState());
        Thread.sleep(Integer.MAX_VALUE);
    }

    private void doSomething() {
        zooKeeper.create("/node_4", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallback(), "创建");
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("收到事件: " + event);
        if (event.getState() == Event.KeeperState.SyncConnected) { // 获取服务端状态
            doSomething();
        }
    }

    // 异步创建回调函数
    static class IStringCallback implements  AsyncCallback.StringCallback {
        /**
         *
         * @param rc 返回码 成功返回0
         * @param path 节点路径
         * @param ctx 创建时传入的上下文
         * @param name 真实路径
         */
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            StringBuffer sb = new StringBuffer();
            sb.append("rc="+rc).append("\n");
            sb.append("path="+path).append("\n");
            sb.append("ctx="+ctx).append("\n");
            sb.append("name="+name);
        }
    }
}
