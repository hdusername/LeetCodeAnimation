import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门准备举办一场王者荣耀表演赛，有 10 名游戏爱好者参与，分为两队，每队 5 人。
 * 每位参与者都有一个评分，代表着他的游戏水平。为了表演赛尽可能精彩，我们需要把 10 名参赛者分为实力尽量相近的两队。
 * 一队的实力可以表示为这一队 5 名队员的评分总和。
 * 现在给你 10 名参与者的游戏水平评分，请你根据上述要求分队，最后输出这两组的实力差绝对值。
 * 例：10 名参赛者的评分分别为：5 1 8 3 4 6 7 10 9 2，分组为（1 3 5 8 10）和（2 4 6 7 9），两组实力差最小，差值为1。有多种分法，但是实力差的绝对值最小为1。
 *
 * 输入描述：
 * 10个整数，表示10名参与者的游戏水平评分。范围在 [1, 10000] 之间。
 *
 * 输出描述：
 * 1个整数，表示分组后两组实力差绝对值的最小值
 *
 */
public class 游戏分组 {

    static List<List<Integer>> groupList = new ArrayList<>();
    static LinkedList<Integer> linkedList = new LinkedList<>();
    static int[] scores;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scores = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        group(0, 5);

        int minAns = Integer.MAX_VALUE;
        //得到总成绩
        int sum = Arrays.stream(scores).sum();

        List<Integer> collect = groupList.stream().map(a -> a.stream().mapToInt(Integer::intValue).sum()).collect(Collectors.toList());

        for(Integer value : collect){
            minAns = Math.min(minAns,Math.abs(sum-(2*value)));
        };

        System.out.println(minAns);
    }

    private static void group(int index, int k) {

        if(linkedList.size()==k){
            groupList.add(new ArrayList<>(linkedList));
            return;
        }
        for(int i =index; i< scores.length-(k-linkedList.size())+1; i++){
            linkedList.add(scores[i]);
            group(i+1, k);
            linkedList.removeLast();
        }
    }
}
