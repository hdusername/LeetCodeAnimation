import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 100个人围成一圈，每个人有一个编码，编号从1开始到100。
 * 他们从1开始依次报数，报到为M的人自动退出圈圈，然后下一个人接着从1开始报数，直到剩余的人数小于M。
 * 请问最后剩余的人在原先的编号为多少？
 *
 * 输入描述：
 * 输入一个整数参数 M
 *
 * 输出描述；
 * 如果输入参数M小于等于1或者大于等于100，输出“ERROR!”；
 * 否则按照原先的编号从小到大的顺序，以英文逗号分割输出编号字符串
 */
public class 报数游戏 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();

        if(m<=1 || m>=100){
            System.out.println("ERROR!");
            return;
        }
        //创建一个双端队列，用于模拟环的遍历
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        int counterNum = 0;
        for(int i=1; i<=100; i++){

            queue.add(i);
        }

        while (queue.size()>=m){
            counterNum++;
            Integer first = queue.pollFirst();
            //如果遍历的次数等于m，重置遍历次数
            if(counterNum == m){
                counterNum=0;
            }else {
                //遍历次数小于m，把移除的第一个元素放到双端队列的后面，用这样的方式模仿环的遍历
                queue.addLast(first);
            }
        }

        StringJoiner stringJoiner = new StringJoiner(",");
        List<Integer> collect = queue.stream().sorted().collect(Collectors.toList());
        for (Integer str : collect){
            stringJoiner.add(str+"");
        }

        System.out.println(stringJoiner);

    }
}
