import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelTest {

    @org.junit.jupiter.api.Test
    void maxParallel() throws InterruptedException {
        Main.Pair pair = Main.getList(20);
        String result = Parallel.maxParallel(10, pair.getFirst());
        assertEquals(result, pair.getSecond());
    }

    @org.junit.jupiter.api.Test
    void maxParallelStream() throws ExecutionException, InterruptedException {
        Main.Pair pair = Main.getList(20);
        String result = Parallel.maxParallelStream(10, pair.getFirst());
        assertEquals(result, pair.getSecond());
    }

}