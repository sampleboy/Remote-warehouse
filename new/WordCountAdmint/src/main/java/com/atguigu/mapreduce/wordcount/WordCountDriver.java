package com.atguigu.mapreduce.wordcount;

import com.atguigu.mapreduce.WordcountMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import javax.xml.soap.Text;
import java.io.IOException;
import java.io.InterruptedIOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedIOException, InterruptedException {
//        获取配置信息以及封装任务
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
//        设置jar加载路径
        job.setJarByClass(WordCountDriver.class);
//        设置map和reduce类
        job.setMapperClass(WordcountMapper.class);
        job.setReducerClass(WordcountReducer.class);
//        设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//        设置最终输出kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
//       设置输入输出的路径
//        FileInputFormat.setInputPaths(job,new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//        提交
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 :1 );

    }

}
