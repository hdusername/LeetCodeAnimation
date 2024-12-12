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

    static int N;
    static int[] pizzs;

    static long[][] pizzsResult;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        pizzs = new int[N];
        pizzsResult = new long[N][N];

        //注意这里的类型是long，因为披萨大小的取值范围是[1, 2147483647]，最大值是int类型的最大正数
        long answer =0;

        for(int i =0;i<N;i++){
            pizzs[i]=scanner.nextInt();
        }

        for (int i=0;i<N;i++){
            //吃货每个都选一遍，getResult方法是获取传入参数范围内吃货所能获得的最大值
            answer = Math.max(answer,pizzs[i]+getResult(check(i-1),check(i+1)));
        }
        System.out.println(answer);
    }

    public static long getResult(int left, int right){
        //因为pizza大小不同，所以不会产生pizzs[left]=pizzs[right]的情况
        if(pizzs[left]>pizzs[right]){
            left = check(left-1);
        }else if(pizzs[left]<pizzs[right]){
            right=check(right+1);
        }

        if(pizzsResult[left][right]!=0){
            return pizzsResult[left][right];
        }

        if(left==right){
            //如果满足这个条件，说明吃货已经选到最后一个pizza了，因为pizza数量是奇数，所以pizza最后一个肯定是吃货选
           pizzsResult[left][right]= pizzs[left];
        }else {

            pizzsResult[left][right] = Math.max(pizzs[left] + getResult(check(left - 1), check(right)), pizzs[right] + getResult(check(left), check(right + 1)));
        }
        return pizzsResult[left][right];

    }

    /**
     * 获取一圈中索引的值
     * @param value
     * @return
     */
    public static int check(int value){
        if(value<0){
            return N-1;
        } else if (value==N) {
            return 0;
        }else {
            return value;
        }
    }
}
