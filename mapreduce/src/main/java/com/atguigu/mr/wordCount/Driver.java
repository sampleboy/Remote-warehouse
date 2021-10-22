package com.atguigu.mr.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


public class Driver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        1.获取job实例；
        Job job = Job.getInstance(new Configuration());
//        2.设置jar包；
        job.setJarByClass(Driver.class);
//        3.设置mapper和reducer
        job.setMapperClass(Mapper.class);
        job.setReducerClass(Reducer.class);
//        4.设置map和reducer的输出类型
        job.setMapOutputKeyClass(Mapper.class);
        job.setMapOutputValueClass(IntWritable.class);


        job.setOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//        5.设置输入输出文件
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
//        6.提交job
        boolean b = job.waitForCompletion(true);

        System.exit(b ? 0 :1);




    }
}
