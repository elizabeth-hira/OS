import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class Parallel {
    private static class MyThread extends Thread {
        private List<String> list;
        private String maxStr;

        MyThread(List<String> list) {
            this.list = list;
            maxStr = "";
        }

        @Override
        public void run() {
            for (String str: list) {
                if(maxStr.length() < str.length())
                    maxStr = str;
            }
        }

        public String getMaxStr() {
            return maxStr;
        }
    }

    static String maxParallel(int count, List<String> list) throws InterruptedException {
        int startPoint = 0;
        MyThread[] threads = new MyThread[count];

        int step = list.size() / count;
        int rem = list.size() % count;

        for(int i = 0; i < count; i++) {
            int add = 0;
            if(rem > 0) {
                add = 1;
                rem--;
            }
            threads[i] = new MyThread(list.subList(startPoint, startPoint + step + add));
            startPoint += step + add;
        }

        for(MyThread thread: threads) {
            thread.start();
        }

        for(MyThread thread: threads) {
            thread.join();
        }

        String str = "";
        for(MyThread thread: threads) {
            if(str.length() < thread.getMaxStr().length())
                str = thread.getMaxStr();
        }

        return str;
    }

    static String maxParallelStream(int count, List<String> list) throws InterruptedException, ExecutionException {
        ForkJoinPool customThreadPool = new ForkJoinPool(count);
        return customThreadPool.submit(() -> list.parallelStream().max((s1, s2)->{
            if(s1.length() > s2.length())
                return 1;
            if(s1.length() < s2.length())
                return -1;
            return 0;
        })).get().get();
    }
}