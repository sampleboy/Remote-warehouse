package com.atguigu.mr.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;

public class WcReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    private IntWritable result= new IntWritable();

    /**
     *框架把数据按照单词分好组输入给我们，我们将同一个单词的次数累加；
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum =0;
        for (IntWritable value : values) {
            sum +=value.get();

//            包装结果并输出；
            result.set(sum);
            context.write(key,result);

        }


    }
}
