import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 有 N 块二手市场收集的银饰，每块银饰的重量都是正整数，收集到的银饰会被熔化用于打造新的饰品。
 * 每一回合，从中选出三块最重的银饰，然后一起熔掉。
 * 假设银饰的重量分别为 x 、y和z，且 x ≤ y ≤ z。那么熔掉的可能结果如下：
 * 如果 x == y == z，那么三块银饰都会被完全熔掉；
 * 如果 x == y 且 y != z，会剩余重量为 z - y 的银块无法被熔掉；
 */
public class 剩余银饰的重量 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        PriorityQueue<Integer> agQueue = new PriorityQueue<>((a,b)->b-a);

        for(int i =0;i<n;i++){
            agQueue.add(scanner.nextInt());
        }
        //Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).forEach(agQueue::offer);

        while (agQueue.size()>=3) {
            Integer z = agQueue.poll();
            Integer y = agQueue.poll();
            Integer x = agQueue.poll();

            int abs = Math.abs((z - y) - (y - x));
            if(abs>0) {
                agQueue.offer(abs);
            }
        }
        if(agQueue.size()>0){
            System.out.println(agQueue.poll());
        }else {
            System.out.println(0);
        }


    }
}
