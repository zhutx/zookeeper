

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Environment;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GetChildrenSync implements Watcher{
	
	
    private static ZooKeeper zooKeeper;
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
				
		zooKeeper = new ZooKeeper("192.168.1.105:2181",5000,new GetChildrenSync());
		System.out.println(zooKeeper.getState().toString());
			
		Thread.sleep(Integer.MAX_VALUE);
		

	}
	
	private void doSomething(ZooKeeper zooKeeper){
		
		try {
			// 获取根节点下所有子节点 watch 是否对子节点的列表变化感兴趣
			List<String> children =  zooKeeper.getChildren("/", true);
			System.out.println(children);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getState()==KeeperState.SyncConnected){
			// 刚创建连接时，事件类型为None，确保doSomething只执行一次
			if (event.getType()==EventType.None && null==event.getPath()){
				doSomething(zooKeeper);
			}else{
				if (event.getType()==EventType.NodeChildrenChanged){
					try {
						System.out.println(zooKeeper.getChildren(event.getPath(), true));
					} catch (KeeperException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}		
		}
	}

}
