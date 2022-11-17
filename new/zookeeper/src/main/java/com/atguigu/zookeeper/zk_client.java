package com.atguigu.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;

public class zk_client {
    private static String connectString =
            "hadoop101:2181,hadoop102:2181,hadoop103:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {

                // 收到事件通知后的回调函数（用户的业务逻辑）
                System.out.println(event.getType() + "--" + event.getPath());

                // 再次启动监听
                try {
                    zkClient.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
