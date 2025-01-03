import java.util.List;
import java.util.concurrent.*;

public class ShutdownNowExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 提交一些任务
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                try {
                    System.out.println("Executing task " + taskNumber);
                    Thread.sleep(2000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskNumber + " was interrupted");
                }
            });
        }

        // 调用 shutdownNow() 停止所有任务
        List<Runnable> notExecutedTasks = executor.shutdownNow();

        // 获取未执行的任务
        System.out.println("Tasks that were not executed:");
        for (Runnable task : notExecutedTasks) {
            System.out.println(task);
        }

        // 等待线程池终止
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("Executor did not terminate in the specified time.");
        } else {
            System.out.println("Executor terminated.");
        }
    }
}

