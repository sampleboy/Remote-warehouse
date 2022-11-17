package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {
    @Test
    public void testMKdirs() throws IOException,InterruptedException, URISyntaxException {

//        获取文件系统，定义对象
        Configuration configuration = new Configuration();
//配置集群运行，干什么
        configuration.set("fs.defaultZFS", "hdfs://hadoop101:8020");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop101:8020"), configuration, "atguigu");
        fs.mkdirs(new Path("/1108/daxian/banzhang"));

        fs.close();
    }
}