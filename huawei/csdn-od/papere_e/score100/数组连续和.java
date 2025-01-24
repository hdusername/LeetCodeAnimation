import java.util.Scanner;

/**
 * 给定一个含有N个正整数的数组, 求出有多少个连续区间（包括单个正整数）, 它们的和大于等于x。
 *
 * 输入描述：
 * 第一行两个整数N x（0 < N <= 100000, 0 <= x <= 10000000)
 * 第二行有N个正整数（每个正整数小于等于100)。
 *
 * 输出描述：
 * 输出一个整数，表示所求的个数。
 * 注意：此题对效率有要求，暴力解法通过率不高，请考虑高效的实现方式。
 */
public class 数组连续和 {
    /**
     * 本题采用数据前缀和的方式实现
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();

        long ans = 0;

        int[] ns = new int[n];
        //存储前缀和
        long[] preSums = new long[n+1];
        for(int i=0; i<n; i++){
            ns[i] = scanner.nextInt();
        }
        for(int i=1; i<preSums.length; i++){
            preSums[i]=preSums[i-1]+ns[i-1];
        }

        for(int i=0; i<preSums.length; i++){
            for(int j=i+1; j<preSums.length; j++){
                if(preSums[j]-preSums[i]>=x){
                    ans+=(preSums.length-j);
                    //从i出发到j为止再往后开始就是符合条件的数组了，当找到后，就跳出对当前i的判断，到下一个i去判断
                    break;
                }
            }

        }

        System.out.println(ans);

    }
}
