package com.atguigu.mr.wordCount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;

public class WcMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    private Text word = new Text();
    private IntWritable one = new IntWritable(1);

    /**
     * 将框架拆成一行行的数据，以（单词，1）的形式存在；
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        获取行信息
        String line = value.toString();
//        将一行拆成很多单词
        String[] words = line.split(" ");
//        将单词写出去
        for (String word : words) {
            this.word.set(word);
        context.write(this.word,this.one);
        }
    }

}
