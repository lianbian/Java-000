package Week_08;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
//输出文件大小
        try {
            System.out.println("file size::" + Files.size(Paths.get("hello.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

//使用Files.lines方法读取20万行数据
        try {
            System.out.println("lines::" + Files.lines(Paths.get("hello.txt")).limit(200000).collect(Collectors.toList()).size());
        } catch (IOException e) {
            e.printStackTrace();
        }
//使用Files.lines方法读取200万行数据
        try {
            System.out.println("lines::" + Files.lines(Paths.get("hello.txt")).limit(2000000).collect(Collectors.toList()).size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        AtomicLong atomicLong = new AtomicLong();
//使用Files.lines方法统计文件总行数
        try {
            Files.lines(Paths.get("hello.txt")).forEach(line->atomicLong.incrementAndGet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("total lines {}" + atomicLong.get());;
    }
}
