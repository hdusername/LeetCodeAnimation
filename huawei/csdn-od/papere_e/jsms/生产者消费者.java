/**
 *多线程实现生产者消费者，堆积满100后停止生产，消费到小于50后继续生产
 */
public class 生产者消费者 {


        // 共享资源类
        static class Warehouse {
            private int products = 0;
            private static final int MAX = 100;
            private static final int MIN = 50;

            public synchronized void produce() throws InterruptedException {
                // 当库存超过最大值时停止生产
                while (products >= MAX) {
                    System.out.println("库存已满，暂停生产");
                    wait();
                }

                products++;
                System.out.println(Thread.currentThread().getName() + " 生产，当前库存：" + products);

                // 唤醒所有等待的消费者（当库存从0变为1时）
                if (products == 1) notifyAll();
            }

            public synchronized void consume() throws InterruptedException {
                // 当库存为0时停止消费
                while (products <= 0) {
                    System.out.println("库存为空，暂停消费");
                    wait();
                }

                products--;
                System.out.println(Thread.currentThread().getName() + " 消费，当前库存：" + products);

                // 当库存低于阈值时唤醒生产者
                if (products < MIN) {
                    System.out.println("库存不足，唤醒生产者");
                    notifyAll();
                }
            }
        }

        public static void main(String[] args) {
            Warehouse warehouse = new Warehouse();

            // 创建3个生产者线程
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    while (true) {
                        try {
                            warehouse.produce();
                            Thread.sleep(1000); // 模拟生产耗时
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "生产者-" + (i+1)).start();
            }

            // 创建5个消费者线程
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    while (true) {
                        try {
                            warehouse.consume();
                            Thread.sleep(1500); // 模拟消费耗时
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }, "消费者-" + (i+1)).start();
            }
        }

}
