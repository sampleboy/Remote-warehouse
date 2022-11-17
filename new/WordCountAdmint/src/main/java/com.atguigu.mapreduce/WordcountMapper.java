package com.atguigu.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.IOException;

public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text word = new Text();
    private IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String[] words = line.split(" ");
        for (String word : words) {
            this.word.set(word);
            context.write(this.word, this.one);
        }

    }
}
