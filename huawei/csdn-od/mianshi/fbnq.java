import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class fbnq

{

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread()+"前序操作");
            return "前需操作结果";
        }, executorService).thenApplyAsync(result -> {
            System.out.println(Thread.currentThread()+"后续操作");
            return "后续操作结果";
        }, executorService);

        stringCompletableFuture.join();  // 等待结果，以便观察日志输出

        executorService.shutdown();
    }
}
