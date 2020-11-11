package Week_04;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HomeWord {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int result = sum();

        System.out.println("同步的计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

//        LocalTime startTime = LocalTime.now();
//        FutureTask<Integer> task1 = new FutureTask<>(new ByCallable());
//        new Thread(task1).start();
//
//        LocalTime endTime = LocalTime.now();
//        long duration = ChronoUnit.MILLIS.between(startTime, endTime);
//        try {
//            System.out.println(
//                    String.format("[实现方式: %s], 返回值为: %d, 执行时间为 %d Millis",
//                            "FutureTask", task1.get(), duration)
//            );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int i) {
        if(i < 2) {
            return 1;
        }

        return fibo(i-1) + fibo(i-2);
    }
}

class ByCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        return Invoke.fibo(36);
    }
}

class Invoke {
    public static int fibo(int i) {
        if(i < 2) {
            return 1;
        }

        return fibo(i-1) + fibo(i-2);
    }
}