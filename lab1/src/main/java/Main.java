import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception{
        List<String> list = getList(1000).getFirst();
        long start, res;

        for(int i = 1; i < 11; i++) {

            start = System.currentTimeMillis();
            String max1 = Parallel.maxParallelStream(i, list);
            res = System.currentTimeMillis() - start;

            System.out.printf("Threads count: %d\nmaxParallelStream function: %dms\n", i, res);

            start = System.currentTimeMillis();
            String max2 = Parallel.maxParallel(i, list);
            res = System.currentTimeMillis() - start;

            System.out.printf("maxParallel function: %dms\n\n", res);
        }
    }
    public static Pair getList(int size){
        String maxStr = "";

        Random r = new Random();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int length = r.nextInt(100);
            StringBuilder sb = new StringBuilder();

            while(sb.length() < length){
                sb.append(Integer.toHexString(r.nextInt()));
            }

            String temp = sb.toString().substring(0, length);

            if(maxStr.length() < temp.length())
                maxStr = temp;

            list.add(temp);
        }

        return new Pair(list, maxStr);
    }
    public static class Pair{
        List<String> first;
        String second;

        public List<String> getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
        public Pair(List<String> first, String second){
            this.first = first;
            this.second = second;
        }
    }
}