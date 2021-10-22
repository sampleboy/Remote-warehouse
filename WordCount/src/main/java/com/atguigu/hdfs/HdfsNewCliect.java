package com.atguigu.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import java.io.IOException;
import java.net.URI;

public class HdfsNewCliect {
    @Test
    public void test() throws IOException, InterruptedException{
//        1.新建HDFS对象；
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop101:8020"),
                new Configuration(), "atguigu");
//        2.操作集群；
        fileSystem.copyFromLocalFile(new Path("E:\\达梦技术支持专用\\库版本\\dm8_20210412_x86_win_64_ent\\dm8_20210412_x86_win_64_ent_8.1.1.190_pack3.iso"),
                new Path("/"));
//        3.关闭资源;
        fileSystem.close();
    }

}
