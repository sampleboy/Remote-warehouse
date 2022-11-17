package com.atguigu.mapreduce.wordcount;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;
import java.io.IOException;


public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    int sum;
    IntWritable v = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable count : values) {
            sum += count.get();
        }
        v.set(sum);
        context.write(key, v);
    }
}