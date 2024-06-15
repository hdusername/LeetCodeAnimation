import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
 */
public class 游戏分组 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] scores = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //排序为了后续去重，因为在递归方法中，比如说一共遍历5层，到第5层时选择的是1，返回后，for循环执行后下一个数字还是1，这时前后选的5位数字是相同的，所以要去重，减少不必要的递归
        Arrays.sort(scores);

        //把所有可以组合成5位数字的和求出来，然后求其和另外5位数字的和的差的绝对值，得到最小分组
        ArrayList<Integer> resultSumList = new ArrayList<>();
        findGroup(scores, 0, 0,0,resultSumList);
        int sum = Arrays.stream(scores).reduce(Integer::sum).orElse(0);
        // 某队实力为subSum，则另一队实力为sum - subSum，则两队实力差为 abs((sum - subSum) - subSum)，先求最小实力差
        System.out.println(resultSumList.stream().map(subSum -> Math.abs(sum - 2 * subSum)).min((a, b) -> a - b).orElse(0));
    }

    /**
     * @param scores 要遍历的集合
     * @param startIndex 从哪个值开始遍历
     * @param level 遍历到第几层了
     * @param sum 当前遍历到的数字相加和是多少
     * @param resultSumList 保存所有相加完的符合条件的和，这里表示保存了各种组合的5个实力的和
     */
    private static void findGroup(int[] scores, int startIndex, int level, int sum, ArrayList<Integer> resultSumList) {
        if(level==5){
            //说明遍历到第五层了，已经遍历到5个数字了
            resultSumList.add(sum);
            return;
        }
        for(int i=startIndex;i<10;i++){
            if(i>startIndex && scores[i]==scores[i-1]){
                //与上面的排序一起用的，如果值相同的话没有必要再进行递归了，因为递归的结果和上一次一样，也就是i和i-1得到的结果一样
                continue;
            }
            findGroup(scores, startIndex+1, level+1, sum+scores[i],resultSumList);
        }
    }

}
