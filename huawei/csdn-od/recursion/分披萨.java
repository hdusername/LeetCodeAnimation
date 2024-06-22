import java.util.Scanner;

/**
 * "吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员
 * 将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
 * 由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
 * 他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
 * 已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
 *
 * 输入描述：
 * 第 1 行为一个正整数奇数 N，表示披萨小块数量。
 * 3 ≤ N < 500
 * 接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
 * 1 ≤ i ≤ N
 * 披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
 * 每块披萨的大小范围为 [1, 2147483647]
 *
 * 输出描述：
 * "吃货"能分得到的最大的披萨大小的总和。
 */
public class 分披萨 {

    static int n ;

    static int[] pizzas;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        long maxPizza = 0;

        pizzas = new int[n];
        for(int i =0;i<n;i++){
            pizzas[i] = scanner.nextInt();
        }

        long[][] pizzaCaches = new long[n][n];

        //分别作为第一块被选出的pizza
        for(int i=0;i<n;i++){

            //这种方式是用递归的方式，容易超时
           // int currPizza = pizzas[i] + getMaxPizza(check(i-1), check(i+1));
            //这种方式是用递归+缓存的方式，也就是动态规划的方式，将一些已经算出来结果的选法缓存起来
            long currPizza = pizzas[i] + getMaxPizza1(check(i-1), check(i+1),pizzaCaches);
            maxPizza = Math.max(currPizza,maxPizza);
        }
        System.out.println(maxPizza);
    }

    private static long getMaxPizza1(int leftIdx, int rightIdx, long[][] pizzaCaches ) {
        //馋嘴拿的,这里不会产生leftIdx=rightIdx的情况，因为最后一块肯定是吃货拿
        if(pizzas[leftIdx]>pizzas[rightIdx]){
            leftIdx = check(leftIdx-1);
        }else {
            rightIdx = check(rightIdx+1);
        }


        if(pizzaCaches[leftIdx][rightIdx]>0){
            return pizzaCaches[leftIdx][rightIdx];
        }

        //这里说明是到了最后一个了，这里是吃货拿，因为披萨是奇数，吃货第一个拿，那么最后一个肯定也是吃货拿
        if(leftIdx == rightIdx){
            pizzaCaches[leftIdx][rightIdx] = pizzas[leftIdx];
        }else {

            pizzaCaches[leftIdx][rightIdx]= Math.max(pizzas[leftIdx] + getMaxPizza1(check(leftIdx - 1), rightIdx,pizzaCaches), pizzas[rightIdx] + getMaxPizza1(leftIdx, check(rightIdx + 1),pizzaCaches));
        }
        return pizzaCaches[leftIdx][rightIdx];
    }

    private static int getMaxPizza(int leftIdx, int rightIdx) {

        //馋嘴拿的
        if(pizzas[leftIdx]>pizzas[rightIdx]){
            leftIdx = check(leftIdx-1);
        }
        if(pizzas[rightIdx]>pizzas[leftIdx]){
            rightIdx = check(rightIdx+1);
        }

        //这里说明是到了最后一个了，这里是吃货拿，因为披萨是奇数，吃货第一个拿，那么最后一个肯定也是吃货拿
        if(leftIdx == rightIdx){
            return pizzas[leftIdx];
        }
        return Math.max(pizzas[leftIdx]+getMaxPizza(check(leftIdx-1),check(rightIdx)), pizzas[rightIdx]+getMaxPizza(check(leftIdx),check(rightIdx+1)));

    }

    public static int check(int i){
        if(i<0){
            return n-1;
        }
        if(i>n-1){
            return 0;
        }
        return i;
    }
}
