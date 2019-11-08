package com.ekuooo.zookeeper.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author: E.KU
 */
public class CuratorDemo {

    private final static String ZK_ADDRESS = "localhost:2181";

    public static void main(String[] args) throws Exception{
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 1))
                .build();
        // 重试策略

        curatorFramework.start();

        // 增删改查
//        curatorFramework.create();
//        curatorFramework.setData();
//        curatorFramework.delete();
//        curatorFramework.getData();

        curatorFramework.create()
                .creatingParentsIfNeeded()
                //节点类型
                .withMode(CreateMode.PERSISTENT)
                .forPath("/data/node2", "test".getBytes());


        curatorFramework.setData().forPath("/data/node2", "12123".getBytes());

    }
}
